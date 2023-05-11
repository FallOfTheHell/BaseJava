package com.resume.webapp;

import com.resume.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResumeTestDataTest {

    @Test
    void getFilledResume() {
        Resume resume = ResumeTestData.getFilledResume("uuid_1", "Gleb");
        assertNotNull(resume);
        assertEquals("uuid_1", resume.getUuid());
        assertEquals("Gleb", resume.getFullName());
    }
}