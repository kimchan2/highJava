package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * annotation에 대하여...
 * 
 * 프로그램 소스코드안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것.(JDK1.5부터 지원)
 * 주석처럼 프로그램 언어에 영향을 미치지 않으면서도 다른 프로그램에게 유용한 정보를 제공함.
 * 
 * 종류 :	1. 표준 애너테이션(주로 컴파일러에게 유용한 정보를 제공하기 위한 애너테이션)
 * 		2. 메사 애너테이션(애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할 때 사용하는 애너테이션) 
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintAnnotation {

}
