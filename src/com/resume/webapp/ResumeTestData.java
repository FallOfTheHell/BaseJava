package com.resume.webapp;

import com.resume.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        testMethod("uuid_1", "Gleb Epifancev");
    }

    public static void testMethod(String uuid, String fullName){
        Resume resume = new Resume(uuid,fullName);

        LocalDate startDateCompany = LocalDate.of(2023, 1, 13);
        LocalDate endDateCompany = LocalDate.of(2024, 1, 13);
        List<Period> periodList = new ArrayList<>();
        periodList.add(new Period("Junior", "Делал то и то и это"
                , startDateCompany, endDateCompany));
        periodList.add(new Period("Middle", "Делал еще больше", startDateCompany, endDateCompany));
        List<Organization> companyList = List.of(new Organization(periodList, "OOO 'Компания'", "что-то да есть"));
        OrganizationSection companySection = new OrganizationSection(companyList);

        LocalDate startDateUniversity = LocalDate.of(2019, 9, 1);
        LocalDate endDateUniversity = LocalDate.of(2024, 5, 25);
        List<Period> educationList = List.of(new Period("Студент", "Обучался прикладной геодезии"
                , startDateUniversity, endDateUniversity));
        List<Organization> universityList = List.of(new Organization(educationList, "СГУГиТ", "sgugit.ru"));
        OrganizationSection universitySection = new OrganizationSection(universityList);

        List<String> achievementsList = new ArrayList<>();
        achievementsList.add("Реализация приложения ResumeStorage");
        achievementsList.add("Учусь на JavaOps с 02.01.2023 года");
        ListSection achievements = new ListSection(achievementsList);

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("Окончил курсы JavaOps - BaseJava");
        ListSection qualification = new ListSection(qualificationList);

        TextSection positionSection = new TextSection("Trainee");

        String personalQualities = "Инициативность, педантичность, скрупулезный";
        TextSection personalSection = new TextSection(personalQualities);

        resume.setContacts(ContactType.PHONE_NUMBER, "+79137114042");
        resume.setContacts(ContactType.EMAIL, "gleb.epifancev@mail.ru");
        resume.setContacts(ContactType.SKYPE, null);
        resume.setContacts(ContactType.USEFUL_LINKS, null);

        resume.setSection(SectionType.EXPERIENCE, companySection);
        resume.setSection(SectionType.ACHIEVEMENT, achievements);
        resume.setSection(SectionType.QUALIFICATIONS, qualification);
        resume.setSection(SectionType.EDUCATION, universitySection);
        resume.setSection(SectionType.OBJECTIVE, positionSection);
        resume.setSection(SectionType.PERSONAL, personalSection);
    }
}
