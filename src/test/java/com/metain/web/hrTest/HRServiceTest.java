package com.metain.web.hrTest;

import com.metain.web.service.VacationService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HRServiceTest {
    @Autowired
    private VacationService vacationService;

}