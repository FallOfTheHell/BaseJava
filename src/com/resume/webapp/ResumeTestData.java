package com.resume.webapp;

import com.resume.webapp.model.*;

import java.util.Date;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Gleb Epifancev");
        Date date1 = new Date();
        Date date2 = new Date();
        List<Period> periodList = List.of(new Period("Junnior", "Делал то и то и это", date1, date2));
        List<Company> companyList = List.of(new Company(periodList, "Крутая компания", "что-то да есть"));
        CompanySection companySection = new CompanySection(resume.getFullName(), SectionType.EXPERIENCE, companyList);
    }
}
