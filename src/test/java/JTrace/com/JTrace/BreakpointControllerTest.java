package JTrace.com.JTrace;

import JTrace.com.JTrace.controller.BreakpointController;
import JTrace.com.JTrace.model.Breakpoint;
import JTrace.com.JTrace.service.BreakpointService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BreakpointControllerTest {

    @Mock
    private BreakpointService breakpointService;

    @InjectMocks
    private BreakpointController breakpointController;


    public void testBreakpoint(){
        Breakpoint breakpoint = new Breakpoint();
        breakpoint.setFileName("Debugger.java");
        breakpoint.setLineNumber(42);
        when(breakpointService.addBreakpoint(breakpoint)).thenReturn(breakpoint);

        Breakpoint result = breakpointController.addBreakpoint(breakpoint);
        verify(breakpointService, times(1)).addBreakpoint(breakpoint);
    }
}
