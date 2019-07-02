package com.seweryn91.CarReservations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CarReservationsApplicationTests {

	@Test
	@Rollback(false)
	public void contextLoads() {
	}

}
