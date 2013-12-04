package com.dv.game.register

import com.dv.game.user.User
import com.dv.game.user.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

import static com.google.common.base.Optional.of
import static org.mockito.Answers.RETURNS_SMART_NULLS
import static org.mockito.BDDMockito.given
import static org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner)
class RegisterControllerTest {

    @InjectMocks
    RegisterController controller

    @Mock(answer = RETURNS_SMART_NULLS)
    BindingResult result

    @Mock(answer = RETURNS_SMART_NULLS)
    UserRepository userRepository

    @Captor
    ArgumentCaptor<ObjectError> errorCaptor

    def model = new RegisterEditModel(
            username: 'user'
    )

    @Test
    void 'Given username already exists, should return error message'() {
        given(userRepository.findUserByName(model.username))
                .willReturn(of(new User('', '', [])))

        controller.register(model, result)

        verify(result).addError(errorCaptor.capture())
        assert 'Username already exists' == errorCaptor.value.defaultMessage
    }

    @Test
    void 'Given username already exists, should return current view'() {
        given(userRepository.findUserByName(model.username))
                .willReturn(of(new User('', '', [])))

        def view = controller.register(model, result)


        assert 'register' == view
    }
}
