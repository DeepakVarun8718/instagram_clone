package in.deepak;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import in.deepak.exception.ErrorDetail;
import in.deepak.exception.PostException;
import in.deepak.exception.UserException;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetail> userExceptionHandler(UserException ue,WebRequest req){
		
		
		ErrorDetail err=new ErrorDetail(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
      System.out.println("--------------------User Excepiton are through ----------------------");
		
		return new ResponseEntity<ErrorDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ErrorDetail> postExceptionHandler(PostException ue,WebRequest req){
		System.out.println("--------------------PostExcepiton are through ----------------------");
		
		ErrorDetail err=new ErrorDetail(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetail> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me,WebRequest req){
		System.out.println("--------------------MethodArgumentNotValidExcepiton are through ----------------------");
		
		ErrorDetail err=new ErrorDetail(me.getBindingResult().getFieldError().getDefaultMessage(),"validation error", LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetail>(err,HttpStatus.BAD_REQUEST);
		
	}
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ue,WebRequest req) throws Exception{
//        
//		System.out.println("--------------------otherExcepiton are through ----------------------");
//		ErrorDetails err=new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
//		
//		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
//		
//	}
}

