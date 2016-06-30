package keyword;

import java.lang.reflect.Method;
import wrappers.LinkedInWrappers;

public class LearnReflection {

	public static void main(String[] args) {

		Class<LinkedInWrappers> wrapper = LinkedInWrappers.class;

		Method[] meth = wrapper.getDeclaredMethods();

		for (Method method : meth) {

			System.out.println(method.getName());
		}

	}

}
