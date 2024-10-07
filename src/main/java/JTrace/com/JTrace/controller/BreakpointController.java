package JTrace.com.JTrace.controller;

import JTrace.com.JTrace.model.Breakpoint;
import JTrace.com.JTrace.service.BreakpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/breakpoints")
public class BreakpointController {

    @Autowired
    private BreakpointService breakpointService;

    @PostMapping("/add")
    public Breakpoint addBreakpoint(@RequestBody Breakpoint breakpoint){
        return breakpointService.addBreakpoint(breakpoint);
    }
    @GetMapping("/all")
    public List<Breakpoint> allBreakpoint(){
        return breakpointService.getAllBreakpoint();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBreakpoint(@PathVariable String id) {
        breakpointService.deleteBreakpoint(id);
    }

    @PutMapping("/update/{id}")
    public Breakpoint updateBreakpoint(@PathVariable String id, @RequestBody Breakpoint breakpoint) {
        return breakpointService.updatebreakpoint(id, breakpoint);
    }
}
