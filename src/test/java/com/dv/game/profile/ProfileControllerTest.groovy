package com.dv.game.profile

import com.dv.game.characters.Character
import com.dv.game.user.User
import com.dv.game.user.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import java.security.Principal

import static com.google.common.base.Optional.of
import static org.mockito.Answers.RETURNS_SMART_NULLS
import static org.mockito.BDDMockito.given
import static org.mockito.Matchers.anyString

@RunWith(MockitoJUnitRunner)
class ProfileControllerTest {

    @InjectMocks
    ProfileController controller

    @Mock(answer = RETURNS_SMART_NULLS)
    Principal principal

    @Mock(answer = RETURNS_SMART_NULLS)
    UserRepository userRepository

    def user = new User(
            username: 'user',
            characters: [
                    new Character(
                            characterName: 'Freeman'
                    )
            ]
    )

    @Test
    void 'Given valid user, should return username'() {
        given(userRepository.findUserByName(anyString()))
                .willReturn(of(user))

        def result = controller.model(principal)

        assert user.username == result.getUsername()
    }

    @Test
    void 'Given valid user, should return character name'() {
        given(userRepository.findUserByName(anyString()))
                .willReturn(of(user))

        def result = controller.model(principal)

        assert user.characters.first().characterName == result.characterNames.first()
    }
}
