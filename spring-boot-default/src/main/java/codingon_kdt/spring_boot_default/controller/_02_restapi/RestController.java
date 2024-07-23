package codingon_kdt.spring_boot_default.controller._02_restapi;

import codingon_kdt.spring_boot_default.controller.dto.UserDTO;
import codingon_kdt.spring_boot_default.controller.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestController {

    // ==== Template 렌더링 ====
    @GetMapping("/")
    public String getReq() {
        return "_02_restapi/req";
    }

    // ==== GET 요청 ====
    // form 1.
    @GetMapping("/get/res1")
    public String getRes1(@RequestParam(value="name") String name, @RequestParam(value="age") int age, Model model) {
        // @RequestParam 어노테이션
        // - HTTP 요청 파라미터를 메서드 매개변수에 바인딩
        // - required=true 가 기본 값이므로 요청 URL 에서 key 를 필수로 보내야함
        //      주의. Spring 은 빈 문자열을 유효한 값으로 취급하므로 name 의 경우, 빈 값을 보내도 에러 발생 x
        System.out.println("[GET] request query String (name) = " + name);
        System.out.println("[GET] request query int (age) = " + age);

        // 뷰에 전달할 데이터를 Model 객체 추가
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        // 응답 결과로 보여줄 뷰 이동
        return "_02_restapi/res";
    }

    // form 2. required=false
    @GetMapping("/get/res2")
    public String getRes2(@RequestParam(value="name", required = false) String name, Model model) {
        System.out.println("[GET] request query String (name) = " + name);

        model.addAttribute("name", name);

        return "_02_restapi/res";
    }

    // form 3. PathVariable
    @GetMapping("/get/res3/{param1}/{param2}")
    public String getRes3(@PathVariable String param1, @PathVariable(value="param2") int age, Model model) {
        // @PathVariable 어노테이션
        // - URL path variable 을 사용할 때 필요
        // - 기본적으로 path variable 은 값을 가져야함. (즉, 값이 없다면 404 에러 발생)

        // 참고. URL 의 path variable 과 해당 메서드의 매개변수명을 다르게 사용하고 싶다면 다음과 같이 사용
        // - ex. @PathVariable(value="param2") int age
        System.out.println("[GET] request query String (name) = " + param1);
        System.out.println("[GET] request query int (age) = " + age);

        model.addAttribute("name", param1);
        model.addAttribute("age", age);

        return "_02_restapi/res";
    }

    // form 4. optional
    // 선택적으로 받아오는 path variable 이 있다면 (age), {} 안에 경로 여러개 삽입
    @GetMapping({"/get/res4/{name}/{age}", "/get/res4/{name}"})
    public String getRes4(@PathVariable String name, @PathVariable(required = false) Integer age, Model model) {
        // path variable 중에서 name (필수), age (선택) 이라면?
        // - required = false 사용
        // - (중요) optional 한 변수가 맨 뒤에 오도록 설정

        // 참고. age 변수에 Integer 타입 지정한 이유
        // - age 는 optional 한 값으로 null 가능함
        // - 따라서, primitive type (int) 이 아닌 reference type 인 wrapper 객체 사용
        System.out.println("[GET] request query String (name) = " + name);
        System.out.println("[GET] request query int (age) = " + age);

        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "_02_restapi/res";
    }

    // ==== POST 요청 ====
    // form 5.
    @PostMapping("/post/res1")
    public String postRes1(@RequestParam String name, @RequestParam int age, Model model) {
        System.out.println("[GET] request query String (name) = " + name);
        System.out.println("[GET] request query int (age) = " + age);

        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "_02_restapi/res";
    }

    // form 6.
    @PostMapping("/post/res2")
    public String postRes2(@RequestParam String name, @RequestParam(required = false) Integer age, Model model) {
        System.out.println("[GET] request query String (name) = " + name);
        System.out.println("[GET] request query int (age) = " + age);

        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "_02_restapi/res";
    }
    
    // form 1 ~ 6 까지는 return 이 항상 Template View

    // - Spring 을 API 서버로 활용하고자 데티어 자체를 응답하고 싶다면?
    // - @ResponseBody 사용

    // form 7.
    @PostMapping("/post/res3")
    @ResponseBody // 메서드 반환값을 응답 본문에 직접 쓰도록 지시
    public String postRes3(@RequestParam String name, @RequestParam int age) {
        System.out.println("[GET] request query String (name) = " + name);
        System.out.println("[GET] request query int (age) = " + age);

        return name + " " + age;
    }


    // ==== DTO 이용하기 ====
    // form 8.
    @GetMapping("/dto/res1")
    @ResponseBody
    public String dtoRes1(@ModelAttribute UserDTO userDTO) {
        // @ModelAttribute UserDTO userDTO
        // - 요청 파라미터를 UserDTO 객체에 바인딩
        // - 폼 input 이름들 (name, age) 이 UserDTO 필드 영어랑 일치해서 자동 매핑
        //      -> 매핑 시 setter 메서드를 사용
        //      -> ?name=s&age=1 -> setName(), setAge() 실행
        System.out.println("[GET] userDTO (name) = " + userDTO.getName());
        System.out.println("[GET] userDTO (age) = " + userDTO.getAge());

        return userDTO.getName() + " " + userDTO.getAge();
    }

    // form 9.
    @PostMapping("/dto/res2")
    @ResponseBody
    public String dtoRes2(UserDTO userDTO) {
        // @ModelAttribute 생략 가능
        // - 파라미터의 UserDTO 타입 앞에 아무것도 없다면 @ModelAttribute 어노테이션 추가
        // - POST 형식이므로 폼 데이터를 자동으로 UserDTO 객체에 바인딩
        System.out.println("[POST] userDTO (name) = " + userDTO.getName());
        System.out.println("[POST] userDTO (age) = " + userDTO.getAge());

        return userDTO.getName() + " " + userDTO.getAge();
    }

    // form 10.
    @PostMapping("/dto/res3")
    @ResponseBody
    public String dtoRes3(@RequestBody UserDTO userDTO) {
        // @RequestBody 어노테이션
        // - 요청 본문에 있는 데이터 (req.body) 를 읽어와서 객체에 매핑
        // - 요청 형식이 JSON 이나 XML 일 때 사용됨
        
        // 참고. POST /dto/res3 요청의 경우, "일반 폼 전송"
        // - 즉, MIME Type 이 application/x-www-form-urlencoded
        // -> 따라서, @RequestBody 어노테이션 사용 시 오류 발생 (사용하는 타입 불일치)
        System.out.println("[POST] userDTO (name) = " + userDTO.getName());
        System.out.println("[POST] userDTO (age) = " + userDTO.getAge());

        return userDTO.getName() + " " + userDTO.getAge();
    }

    // ==== VO 이용하기 ====
    // form 11.
    @GetMapping("/vo/res1")
    @ResponseBody
    public String voRes1(@ModelAttribute UserVO userVO) {
        // @ModelAttribute 어노테이션
        // - 요청 파라미터를 UserVO 객체에 바인딩
        
        // 참고. 브라우저에서 응답이 "null 0" 으로 나오는 이유?
        // - @ModelAttribute 어노테이션은 setter 메서드를 통해 객체에 값을 주입
        // - 하지만, UserVO 에는 setter 메서드 없으므로 폼에서 전송한 데이터가 주입되지 않음
        // -> 따라서, name, age 필드는 초기화되지 않은 상태 null 과 0 으로 남게됨
        System.out.println("[GET] userVO (name) = " + userVO.getName());
        System.out.println("[GET] userVO (age) = " + userVO.getAge());

        return userVO.getName() + " " + userVO.getAge();
    }

    // form 12.
    @PostMapping("/vo/res2")
    @ResponseBody
    public String voRes2(UserVO userVO) {
        // @ModelAttribute 어노테이션 생략 가능 -> 자동 적용
        // - setter 메서드를 이용해서 UserVO 객체에 값을 주입
        // -> VO 객체는 setter 메서드를 가지지 않으므로 데이터 바인딩이 제대로 이뤄지지 않음
        System.out.println("[POST] userVO (name) = " + userVO.getName());
        System.out.println("[POST] userVO (age) = " + userVO.getAge());

        return userVO.getName() + " " + userVO.getAge();
    }

    // form 13.
    // type=Unsupported Media Type, status=415 에러 발생
    // 참고. HTTP Status code 415 (Unsupported Media Type)
    // - 서버가 클라이언트로부터 받은 요청의 미디어 타입 (Content-Type) 을 지원하지 않거나 이해할 수 없다는 의미
    @PostMapping("/vo/res3")
    @ResponseBody
    public String voRes3(@RequestBody UserVO userVO) {
        // @RequestBody UserVO userVO
        // - 요청의 본문 데이터를 UserVO 객체로 변환 시도

        // 참고. @RequestBody 어노테이션은 주로 JSON 이나 XML 형식의 데이터를 처리하기 위해 사용
        // - "일반 폼 전송" 사용하고 있으므로 MIME Type 은 "application/x-www-form-urlencoded"
        // - 두 형식이 일치하지 않으므로 Spring 이 이 요청 본문을 UserVO 객체로 변환 불가능
        System.out.println("[POST] userVO (name) = " + userVO.getName());
        System.out.println("[POST] userVO (age) = " + userVO.getAge());

        return userVO.getName() + " " + userVO.getAge();
    }

    // ==== Axios with DTO (동적 폼 전송) ====
    // form 14 - 1.
    @GetMapping("/axios/res1")
    @ResponseBody
    public String axiosRes1(@RequestParam String name, @RequestParam String age) {
        // @RequestParam
        // - HTTP 요청 파라미터를 메서드 매개변수에 바인딩
        // - 쿼리 스트링 감지
        System.out.println("[GET] axios (name) = " + name);
        System.out.println("[GET] axios (age) = " + age);

        return "이름: " + name + ", 나이: " + age;
    }

    // form 14 - 2.
    @GetMapping("/axios/res2")
    @ResponseBody
    public String axiosRes2(UserDTO userDTO) {
        // UserDTO 객체를 파라미터로 받아 자동으로 데이터 바인딩
        // - DTO 를 사용해서 데이터 캡슐화
        // - DTO 사용 시 코드가 깔끔 (파라미터 n 개를 dto 1개로 처리 가능) 해지고, 데이터 구조 확장 쉽다.
        System.out.println("[GET] axios and dto (name) = " + userDTO.getName());
        System.out.println("[GET] axios and dto (age) = " + userDTO.getAge());

        return "이름: " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    }

    // form 15 - 1.
    @PostMapping("/axios/res3")
    @ResponseBody
    public String axiosRes3(@RequestParam String name, @RequestParam String age) {
        // 참고. Request failed with status code 400 에러 발생
        // - 클라이언트에서는 데이터를 객체로 전송하지만, 서버에서는 @RequestParam 을 사용
        // - Axios 는 기본적으로 데이터를 JSON 형식으로 전송 (Content-Type: application/json)
        // - 하지만, 서버 측 코드는 @RequestParam 을 사용해 데이터를 받음 (Content-Type: application/x-www-form-urlencoded)
        // -> 타입 불일치
        System.out.println("[POST] axios (name) = " + name);
        System.out.println("[POST] axios (age) = " + age);

        return "이름: " + name + ", 나이: " + age;
    }

    // form 15 - 2.
    @PostMapping("/axios/res4")
    @ResponseBody
    public String axiosRes4(UserDTO userDTO) {
        // 참고. @RequestBody 어노테이션 없이 UserDTO 객체로 파라미터를 받고자 함
        // - 해당 어노테이션이 없으면 Spring 은 JSON 요청 본문을 자동으로 UserDTO 에 매핑되지 않음
        System.out.println("[POST] axios and dto (name) = " + userDTO.getName());
        System.out.println("[POST] axios and dto (age) = " + userDTO.getAge());

        return "이름: " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    }

    // form 15 - 3.
    @PostMapping("/axios/res5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDTO userDTO) {
        // @RequestBody UserDTO userDTO
        // - 요청의 본문 JSON 데이터를 UserDTO 객체로 매핑
        // - RESTful API 설계에 적합하며, 클라이언트-서버 간 데이터 교환을 명확하게 함
        // -> form 15 -2 컨트롤러 메서드에 대한 해결책
        System.out.println("[POST] axios and dto (name) = " + userDTO.getName());
        System.out.println("[POST] axios and dto (age) = " + userDTO.getAge());

        return "이름: " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    }

    // ==== Axios with VO (동적 폼 전송) ====
    // form 16 - 1.
    @GetMapping("/axios/vo/res1")
    @ResponseBody
    public String axiosVoRes1(@RequestParam String name, @RequestParam String age) {
        // @RequestParam
        // - HTTP 요청 파라미터를 메서드 매개변수에 바인딩
        // - 쿼리 스트링 감지
        System.out.println("[GET] axios (name) = " + name);
        System.out.println("[GET] axios (age) = " + age);

        return "이름: " + name + ", 나이: " + age;
    }

    // form 16 - 2.
    @GetMapping("/axios/vo/res2")
    @ResponseBody
    public String axiosVoRes2(UserVO userVO) {
        // @ModelAttribute 어노테이션 생략함 -> 자동 적용
        // - 해당 어노테이션은 setter 객체를 통해 객체에 값을 주입
        // -> 그러나, vo 객체에는 setter 메서드가 없으므로 바인딩이 이뤄지지 않음
        System.out.println("[GET] axios and vo (name) = " + userVO.getName());
        System.out.println("[GET] axios and vo (age) = " + userVO.getAge());

        return "이름: " + userVO.getName() + ", 나이: " + userVO.getAge();
    }

    // form 17 - 1.
    @PostMapping("/axios/vo/res3")
    @ResponseBody
    public String axiosVoRes3(@RequestParam String name, @RequestParam String age) {
        // 참고. Request failed with status code 400 에러 발생
        // @RequestParam 어노테이션
        // - 주로 application/x-www-form-urlencoded 형식의 데이터를 처리하는 데 사용
        // -> 현재 프론트에서 JSON 형식으로 요청의 바디에 데이터를 보내고 있음
        // - 타입 불일치
        System.out.println("[POST] axios (name) = " + name);
        System.out.println("[POST] axios (age) = " + age);

        return "이름: " + name + ", 나이: " + age;
    }

    // form 17 - 2.
    @PostMapping("/axios/vo/res4")
    @ResponseBody
    public String axiosVoRes4(UserVO userVO) {
        // @ModelAttribute 어노테이션 생략함 -> 자동 적용
        // - 해당 어노테이션은 setter 메서드를 통해 객체에 값을 주입
        // -> 하지만, VO 객체에는 setter 메서드가 없으므로 데이터 바인딩이 이워지지 않음
        
        // 참고. 이름/나이가 null/0 값으로 나오는 이유
        // - @ModelAttribute 는 주로 폼 데이터나 쿼리 파라미터를 바인딩하는 데 사용
        // - 프론트에서 JSON 형식으로 전송하고 있기 때문에 바인딩이 제대로 되지 않음
        System.out.println("[POST] axios and vo (name) = " + userVO.getName());
        System.out.println("[POST] axios and vo (age) = " + userVO.getAge());

        return "이름: " + userVO.getName() + ", 나이: " + userVO.getAge();
    }

    // form 17 - 3.
    @PostMapping("/axios/vo/res5")
    @ResponseBody
    public String axiosVoRes5(@RequestBody UserVO userVO) {
        // @RequestBody UserVO userVO
        // - JSON 형식의 본문을 USerVO 객체로 올바르게 변환하도록 함
        // - @RequestBody 는 setter 를 이용하지 않음
        // -> 라이브러리를 써서 각 필드에 직접 접근해 값을 주입하면서 매핑하기 때문에 setter 가 없어도 정상 작동함
        System.out.println("[POST] axios and vo (name) = " + userVO.getName());
        System.out.println("[POST] axios and vo (age) = " + userVO.getAge());

        return "이름: " + userVO.getName() + ", 나이: " + userVO.getAge();
    }

}
