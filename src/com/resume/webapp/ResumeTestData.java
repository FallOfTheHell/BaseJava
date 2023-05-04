package com.resume.webapp;

import com.resume.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.PHONE_NUMBER, "+79137114042");
        contacts.put(ContactType.EMAIL, "gleb.epifancev@mail.ru");
        contacts.put(ContactType.SKYPE, null);
        contacts.put(ContactType.USEFUL_LINKS, null);

        LocalDate startDateCompany = LocalDate.of(2023, 1, 13);
        LocalDate endDateCompany = LocalDate.of(2024, 1, 13);
        List<Period> periodList = List.of(new Period("Junior", "Делал то и то и это"
                , startDateCompany, endDateCompany));
        List<Company> companyList = List.of(new Company(periodList, "Крутая компания", "что-то да есть"));
        CompanySection companySection = new CompanySection(companyList);

        LocalDate startDateUniversity = LocalDate.of(2019, 9, 1);
        LocalDate endDateUniversity = LocalDate.of(2024, 5, 25);
        List<Period> educationList = List.of(new Period("Студент", "Обучался прикладной геодезии"
                , startDateUniversity, endDateUniversity));
        List<Company> universityList = List.of(new Company(educationList, "СГУГиТ", "sgugit.ru"));
        CompanySection universitySection = new CompanySection(universityList);

        List<String> achievementsList = new ArrayList<>();
        achievementsList.add("Реализация приложения ResumeStorage");
        achievementsList.add("Учусь на JavaOps с 02.01.2023 года");
        ListSection achievements = new ListSection(achievementsList);

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("Окончил курсы JavaOps - BaseJava");
        ListSection qualification = new ListSection(qualificationList);

        String position = "Trainee";
        TextSection positionSection = new TextSection(position);

        String personalQualities = "Инициативность, педантичность, скрупулезный";
        TextSection personalSection = new TextSection(personalQualities);

        EnumMap<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        sections.put(SectionType.EXPERIENCE, companySection);
        sections.put(SectionType.ACHIEVEMENT, achievements);
        sections.put(SectionType.QUALIFICATIONS, qualification);
        sections.put(SectionType.EDUCATION, universitySection);
        sections.put(SectionType.OBJECTIVE, positionSection);
        sections.put(SectionType.PERSONAL, personalSection);

        Resume resume = new Resume("Gleb Epifancev");
        resume.getContacts();
        System.out.println(resume);
    }
}
