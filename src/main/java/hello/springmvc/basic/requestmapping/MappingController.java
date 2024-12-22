package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	/*
	 * 어떤 HTTP 메서드가 와도 다 받아줌.
	 */
	@RequestMapping("/hello-basic")
	public String helloBasic() {
		log.info("hello Basic");
		return "ok";
	}
	
	/*
	 * @RequestMapping 애너테이션을 사용할 때, method 속성을 지정하면
	 * 그 특정 HTTP 메서드에 대해서만 요청을 처리하도록 제한할 수 있다.
	 * 
	 * 그렇기 때문에 해당 엔드포인트에 지정한 메서드가 아닌 다른 요청을 보내면 
	 * 405 Method Not Allowed 상태코드가 반환된다. 
	 * 	ㄴ> i.e. "요청한 메서드는 허용되지 않음" 
	 *
	 */
//	@GetMapping("/mapping-get-v1")
	@RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
	public String mappingGetV1() {
		log.info("mappingGetV1");
		return "ok";
	}
	
	/*
	 * 축약 애너테이션 
	 *  ㄴ> 코드 내부에서 @RequestMapping과 method 를 지정해서 사용
	 *  
	 *  @RequestMapping(method = RequestMethod.GET)
	 *   public @interface GetMapping { ... }
	 */
	@GetMapping("/mapping-get-v2")
	public String mappingGetV2() {
		log.info("mappingGetV2");
		return "ok";
	}
	
	/*
	 * PathVariable(경로 변수) 사용
	 * 
	 * @PathVariable을 사용할 때, @GetMapping과 @PathVariable을 함께 사용하는 경우,
	 * @PathVariable의 변수 이름을 메서드 파라미터와 동일하게 만들면, 
	 * @PathVariable("userId")와 같은 명시적 이름을 생략할 수 있다.
	 */
	@GetMapping("/mapping/{userId}")
	public String mappingPathVar(@PathVariable("userId") String userId) {
		log.info("mappingPathVar userId = {}", userId);
		return "ok";
	}
	
	/*
	 * PathVariable 사용 다중
	 */
	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPathVar(@PathVariable("userId") String userId, 
			@PathVariable("orderId") Long orderId) {
		log.info("mappingPathVar userId = {}, orderId = {}", userId, orderId);
		return "ok";
	}
	
	/**
	* 파라미터로 추가 매핑
	* params="mode",
	* params="!mode"
	* params="mode=debug"
	* params="mode!=debug" (! = )
	* params = {"mode=debug","data=good"}
	  */
	@GetMapping(value = "/mapping-param", params = "mode=debug")
	public String mappingParam() {
	    log.info("mappingParam");
	    return "ok";
	}
	
}
