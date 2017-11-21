package test;

import java.lang.reflect.InvocationTargetException;

public class Main {

	static void p() { System.out.println(); }
	static void p(String msg) { System.out.println(msg); }

	public static void main(String args[]) {
		String[] classNames = {
				// Fields
				"FieldPresence",
				"FieldUseOne",
				"FieldUseBoth",
				// Methods
				"MethodStaticPresence",
				"MethodPrivatePresence",
				"MethodStaticUseOne",
				"MethodPrivateUseOne",
				"MethodStaticUseBoth",
				"MethodPrivateUseBoth",
		};
		for (String className : classNames) {
			p(className + ":");
			try {
				Class<?> c = Class.forName(Main.class.getName() + "$" + className);
				c.getDeclaredMethod("main", String[].class).invoke(null, new Object[]{args});
			} catch (InvocationTargetException e) {
				e.getCause().printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
			}
			p();
		}
	}

	// Fields

	public static class FieldPresence {
		private static int f;
		public static void main(String args[]) { p("main"); }
	}

	public static class FieldUseOne {
		private static int f;
		public static void main(String args[]) { p("main"); }
	}

	public static class FieldUseBoth {
		private static int f = 1;
		public static void main(String args[]) { p("static f: " + f); }
	}

	// Methods

	public static class MethodStaticPresence {
		static void m() {}
		public static void main(String args[]) { p("main"); }
	}

	public static class MethodPrivatePresence {
		private void m() {}
		public static void main(String args[]) { p("main"); }
	}

	public static class MethodStaticUseOne {
		static void m() { p("static m"); }
		public static void main(String args[]) { m(); }
	}

	public static class MethodPrivateUseOne {
		private void m() { p("private m"); }
		public static void main(String args[]) { new MethodPrivateUseOne().m(); }
	}

	public static class MethodStaticUseBoth {
		static void m() { p("static m"); }
		public static void main(String args[]) { m(); }
	}

	public static class MethodPrivateUseBoth {
		private void m() { p("private m"); }
		public static void main(String args[]) { new MethodPrivateUseBoth().m(); }
	}

}
