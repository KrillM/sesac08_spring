package com.sesac.sesacspring.controller;

import com.sesac.sesacspring.dto.UserDto;
import com.sesac.sesacspring.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController  @Controller + @ResponseBody, @Controller와 같이 사용하지 아니한다.
public class RestControllerPractice {
    @GetMapping("/")
    public String getMain(){
        return "request";
    }

    @GetMapping("/get/response1")
    // ?key=value
    // ?name=
    // @RequestParam의 기본값은 required=true
    // 알 수없는 에러로 (value = "name")로 사용한다.
    public String getResponse1(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "response";
    }

    @GetMapping("/get/response2")
    // ?search=검색어
    // ?search=검색어&hashtag=코딩
    public String getResponse2(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "response";
    }

    @GetMapping("/get/response3/{param1}/{param2}")
    // url안에 @PathVariable를 사용하면 값을 포함할 수 있다.
    public String getResponse3(@PathVariable (value = "param1") String name,
                               @PathVariable (value = "param2") String age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "response";
    }

    @GetMapping({"/get/response4/{param1}", "/get/response4/{param1}/{param2}"})
    // 만약 pathVariable을 보낼 때 선택적으로 처리해야 한다면
    // pathVariable 역시 기본값이 required=true이다.
    // 두 개의 주소를 적고 required=false로 설정해야 한다.
    // 중요! optional한 parameter는 멘 뒤에 오도록 설정한다.
    public String getResponse4(@PathVariable (value = "param1") String name,
                               @PathVariable (value = "param2", required = false) String age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "response";
    }

    // Post 방식 - @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(
            @RequestParam(value = "name") String n, @RequestParam(value = "age") String a, Model model){
        model.addAttribute("name", n);
        model.addAttribute("age", a);
        return "response";
    }

    @PostMapping("/post/response2")
    public String postResponse2(
            @RequestParam(required = false, value = "name") String n,
            @RequestParam(required = false, value = "age") String a, Model model){
        model.addAttribute("name", n);
        model.addAttribute("age", a);
        return "response";
    }

    // @ResponseBody
    // 응답시 객체를 json 형태로 리턴한다. (직렬화)
    // express res.send와 유사
    @PostMapping("/post/response3")
    @ResponseBody
    public String postResponse3(
            @RequestParam(required = false, value = "name") String n,
            @RequestParam(required = false, value = "age") String a, Model model){
        model.addAttribute("name", n);
        model.addAttribute("age", a);
        return n + " - " + a;
    }

    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse1(@ModelAttribute UserDto userDto){
        // DTO: getter와 setter가 있는 객체
        // Get 방식에서 DTO 객체로 담아서 값이 받아진다.
        // DTO를 가져와 사용하면 @ModelAttribute를 가져와 사용하는 기능을 가진다.
        // @ModelAttribute : HTML 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑
        // ?name=크릴&age=22 -> setName("크릴") setAge("22")
        return userDto.getName() + " " + userDto.getAge();
    }

    // @RequestBody : 요청의 본문에 있는 데이터(body)를 받는다.
    @GetMapping("/dto/response11")
    @ResponseBody
    public String dtoResponse11(@RequestBody UserDto userDto){
        return userDto.getName() + " " + userDto.getAge();
    }

    // 일반 폼 전송 -> www-x-form-urlencoded => 쿼리 매개변수
    // 일반 폼 전송 => ReqyestBody 값을 처리하지 못한다
    // 왜냐하면 RequestBody는 요청의 본문에 있는 데이터(body)를 처리할 수 있기 때문이다
    // 그러므로 json, xml일때만 실행이 가능하다
    // application/json

    // 일반 폼 전송 - DTO (getter, setter 모두 있는 녀석)
    // 1) 어노테이션 없이 DTO로 받을 경우 -> O
    // 2) @ModelAttribute DTO 받을 경우 -> O
    // 3) @RequestBody DTO 받을 경우 -> 오류

    // 일반 폼 전송은 www-x-form-urlencoded 형식 이므로 어떤 형태이든 요청의 본문에 데이터가 아닌
    // 폼 데이터 형태로 url로 데이터가 전송이 된다.
    // 다시말해 일반적인 폼 전송으로는 RequestBody로 사용이 불가능하다.

    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVo userVo){
        return userVo.getName() + " " + userVo.getAge();
    } // => null

    @PostMapping("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVo userVo){
        return userVo.getName() + " " + userVo.getAge();
    } // => null

    @PostMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVo userVo){
        return userVo.getName() + " " + userVo.getAge();
    } // => error

    // axios를 이용한 데이터 처리

    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age){
        return name + " " + age;
    } // axios - get - @RequestParam -> O

    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDto userDto){
        // DTO를 통한 @ModelAttribute사용
        // axios = application/json
        return userDto.getName() + " " + userDto.getAge();
    } // axios - get - @ModelAttribute -> O

    @PostMapping("/axios/response3")
    // url이었는데, axios post는 url에 데이터가 X
    // url에 아무것도 없는데 name, age required=true이기 때문에 에러가 발생한다.
    @ResponseBody
    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        return "이름: " + name + ", 나이: "+ age;
    }

    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosRes4(UserDto userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    }
    // axios + post 데이터 -> @RequestBody O(null)
    // ModelAttribute를 이용해 데이터를 보냈을 때 값이 null
    // axios로 보내면 url로 데이터를 보내는 게 아니라 본문으로 데이터를 보낸다,
    // 즉, @ModelAttribute가 값을 볼 수가 없다.

    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDto userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    }
    // axios + post 데이터 -> @RequestBody O

    // ========== VO 이용 with. axios ==========
    @GetMapping("/axios/vo/response1")
    @ResponseBody
    public String axiosVoRes1(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosVoRes2(UserVo userVO) {
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response3")
    @ResponseBody
    public String axiosVoRes3(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosVoRes4(UserVo userVO){
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosVoRes5(@RequestBody UserVo userVO){
        // axios post로 데이터를 보내면 요청의 본문(body)에 데이터가 들어간다.
        // @requestBody는 요청의 본문에 있는 데이터를 읽을 수 있다.
        // UserVo 클래스는 setter 메소드가 없다.
        // @RequestBody는 데이터를 각각의 필드(변수)에 직접적으로 값을 주입한다.
        // @RequestBody는 UserVo, UserDto 구별없이 setter 메소드 유무 관계없이 변수에 값을 넣을 수 있다.
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }
}
