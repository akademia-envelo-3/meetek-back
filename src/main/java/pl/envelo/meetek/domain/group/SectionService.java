package pl.envelo.meetek.domain.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.envelo.meetek.domain.event.RecurringEventSetService;
import pl.envelo.meetek.domain.event.SingleEventService;
import pl.envelo.meetek.domain.event.model.*;
import pl.envelo.meetek.domain.group.model.Section;
import pl.envelo.meetek.domain.group.model.SectionCreateDto;
import pl.envelo.meetek.domain.group.model.SectionLongDto;
import pl.envelo.meetek.domain.group.model.SectionShortDto;
import pl.envelo.meetek.domain.user.model.StandardUser;
import pl.envelo.meetek.utils.DtoMapperService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SectionService {

    private final SectionRepo sectionRepo;
    private final SectionValidator sectionValidator;
    private final DtoMapperService mapperService;
    private final SingleEventService eventService;
    private final RecurringEventSetService eventSetService;

    @Transactional(readOnly = true)
    public SectionLongDto getSectionById(long id) {
        Section section = sectionValidator.validateExists(id);
        Section sectionWithMembersCount = setCountMembers(section);
        return mapperService.mapToSectionLongDto(sectionWithMembersCount);
    }

    @Transactional
    public SectionShortDto createSection(StandardUser user, SectionCreateDto sectionDto) {
        Section section = mapperService.mapToSection(sectionDto);
        sectionValidator.validateNotActiveDuplicate(section.getName());
        section.setGroupOwner(user);
        section.setIsActive(true);
        sectionValidator.validateInput(section);
        section = sectionRepo.save(section);
        return mapperService.mapToSectionShortDto(section);
    }

    @Transactional
    public void editSection(long sectionId, SectionLongDto sectionDto, StandardUser requester) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateUserAuthorized(section, requester);
        Section sectionFromDto = mapperService.mapToSection(sectionDto);
        sectionValidator.validateNotActiveDuplicate(section, sectionFromDto.getName());
        sectionFromDto.setGroupOwner(sectionValidator.validateOwner(section, sectionFromDto.getGroupOwner()));
        updateFields(section, sectionFromDto);
        sectionValidator.validateInput(section);
        sectionRepo.save(section);
    }

    @Transactional
    public void deactivateSection(long sectionId, StandardUser requester) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateUserAuthorized(section, requester);
        requester.getOwnedGroups().remove(section);
        section.getJoinedUsers().forEach(user -> user.getJoinedGroups().remove(section));
        section.setGroupOwner(null);
        section.setIsActive(false);
        sectionRepo.save(section);
    }

    @Transactional(readOnly = true)
    public List<SectionShortDto> getAllActiveSections() {
        List<Section> sections = sectionRepo.findAllByIsActiveTrueOrderByName();
        List<Section> sectionsWithMembersCount = setCountMembers(sections);
        return sectionsWithMembersCount.stream()
                .map(mapperService::mapToSectionShortDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SectionShortDto> getAllJoinedSections(long userId) {
        List<Section> sections = sectionRepo.findAllJoinedSections(userId);
        List<Section> sectionsWithMembersCount = setCountMembers(sections);
        return sectionsWithMembersCount.stream()
                .map(mapperService::mapToSectionShortDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SectionShortDto> getAllOwnedSectionsByUserId(long userId) {
        List<Section> sections = sectionRepo.findAllOwnedSections(userId);
        List<Section> sectionsWithMembersCount = setCountMembers(sections);
        return sectionsWithMembersCount.stream()
                .map(mapperService::mapToSectionShortDto)
                .toList();
    }

    @Transactional
    public void setSectionOwnerByAdmin(long newOwnerId, long sectionId) {
        Section section = sectionValidator.validateExists(sectionId);
        StandardUser newOwner = sectionValidator.validateOwnerForAdmin(section, newOwnerId);
        sectionRepo.updateOwner(section.getGroupId(), newOwner.getParticipantId());
    }

    @Transactional
    public void joinSection(StandardUser user, long sectionId) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateActive(section);
        sectionValidator.validateUserNotMember(user, section);
        section.getJoinedUsers().add(user);
        sectionRepo.save(section);
    }

    @Transactional
    public void leaveSection(StandardUser user, long sectionId) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateUserMember(user, section);
        section.getJoinedUsers().remove(user);
        sectionRepo.save(section);
    }

    private Section setCountMembers(Section section) {
        section.setMembersCount(section.getJoinedUsers().size());
        return section;
    }

    private List<Section> setCountMembers(List<Section> sections) {
        return sections.stream().map(this::setCountMembers).toList();
    }

    private void updateFields(Section section, Section sectionFromDto) {
        section.setGroupOwner(sectionFromDto.getGroupOwner());
        section.setName(sectionFromDto.getName());
        section.setDescription(sectionFromDto.getDescription());
    }

    @Transactional
    public List<SingleEventShortDto> getAllEventsOfSection(long sectionId, String time) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateActive(section);
        return eventService.getAllEventsFromSection(sectionId, time);
    }

    @Transactional
    public RecurringEventSetDto createEventSet(StandardUser user, long sectionId, RecurringEventSetCreateDto eventSetCreateDto) {
        Section section = sectionValidator.validateExists(sectionId);
        sectionValidator.validateUserMember(user, section);
        SingleEvent event = mapperService.mapToSingleEvent(eventSetCreateDto);
        RecurringEventSet eventSet = eventSetService.createRecurringEventSet(user, eventSetCreateDto, event);
        addEventSetToSection(section, eventSet);
        addEventsToSection(section, eventSet);
        sectionRepo.save(section);
        return mapperService.mapToRecurringEventSetDto(eventSet);
    }

    private void addEventSetToSection(Section section, RecurringEventSet eventSet) {
        Set<RecurringEventSet> recurringEventSets = section.getRecurringEvents();
        recurringEventSets.add(eventSet);
        section.setRecurringEvents(recurringEventSets);
    }

    private void addEventsToSection(Section section, RecurringEventSet eventSet) {
        Set<SingleEvent> events = section.getEvents();
        events.addAll(eventSet.getEvents());
        section.setEvents(events);
    }


}

