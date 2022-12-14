package buffett.weallarebuffett.controller;

import buffett.weallarebuffett.model.MemberDto;

import javax.validation.Valid;

import buffett.weallarebuffett.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String join(Model model) {
        model.addAttribute("member", new MemberDto());

        return "members/createMemberForm";
    }

    @PostMapping(value = "new")
    public String joinProc(@Valid MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        memberService.join(memberDto);
        return "/notice/main";
    }
}
