package com.dv.game.login

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

import static org.mockito.Answers.RETURNS_SMART_NULLS
import static org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner)
class LoginControllerTest {

    @InjectMocks
    LoginController controller

    @Mock(answer = RETURNS_SMART_NULLS)
    BindingResult result

    @Captor
    ArgumentCaptor<ObjectError> errorCaptor

    @Test
    void 'Should populate error message on invalid credentials'() {
        controller.error(null, result)

        verify(result).addError(errorCaptor.capture())
        assert 'command' == errorCaptor.value.objectName
    }
}
