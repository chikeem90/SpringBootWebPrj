package io.chikeem90.github.springboot.web;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

public class ProfilesControllerUnitTest {
	@Test
	public void real_profile이_조회된다() {
		// given
		String expectedProfile = "real";
		MockEnvironment env = new MockEnvironment();
		env.addActiveProfile(expectedProfile);
		env.addActiveProfile("oauth");
		env.addActiveProfile("real-db");

		ProfilesController controller = new ProfilesController(env);

		// when
		String profile = controller.profile();

		// then
		assertThat(profile).isEqualTo(expectedProfile);
	}
	
	@Test
	public void real_profile이_없으면_첫번째가_조회된다() throws Exception {
	    // given
	    String expectedProfile = "oauth";
	    MockEnvironment env = new MockEnvironment();

	    env.addActiveProfile(expectedProfile);
	    env.addActiveProfile("real-db");

	    ProfilesController controller = new ProfilesController(env);

	    // when
	    String profile = controller.profile();

	    // then
		assertThat(profile).isEqualTo(expectedProfile);
	}
	
	@Test
	public void active_profile이_없으면_default가_조회된다() throws Exception {
	    // given
	    String expectedProfile = "default";
	    MockEnvironment env = new MockEnvironment();
	    ProfilesController controller = new ProfilesController(env);

	    // when
	    String profile = controller.profile();

	    // then
		assertThat(profile).isEqualTo(expectedProfile);
	}
}