package test;

import lanchon.dexpatcher.annotation.*;

@DexIgnore
public class Main {

	static void p(String msg) { throw null; }

	// Fields

	@DexEdit
	public static class FieldPresence {
		@DexAdd
		private int f;
	}

	@DexEdit
	public static class FieldUseOne {
		@DexAdd
		private int f = 42;
		@DexReplace
		public FieldUseOne() {}
		@DexAppend
		public static void main(String args[]) { p("instance f: " + new FieldUseOne().f); }
	}

	@DexEdit
	public static class FieldUseBoth {
		@DexAdd
		private int f = 42;
		@DexReplace
		public FieldUseBoth() {}
		@DexAppend
		public static void main(String args[]) { p("instance f: " + new FieldUseBoth().f); }
	}

	// Methods

	@DexEdit
	public static class MethodStaticPresence {
		@DexAdd
		public void m() {}
	}

	@DexEdit
	public static class MethodPrivatePresence {
		@DexAdd
		public void m() {}
	}

	@DexEdit
	public static class MethodStaticUseOne {
		@DexAdd
		public void m() { p("public m"); }
		@DexAppend
		public static void main(String args[]) { p("main"); }
	}

	@DexEdit
	public static class MethodPrivateUseOne {
		@DexAdd
		public void m() { p("public m"); }
		@DexAppend
		public static void main(String args[]) { p("main"); }
	}

	@DexEdit
	public static class MethodStaticUseBoth {
		@DexAdd
		public void m() { p("public m"); }
		@DexAppend
		public static void main(String args[]) { new MethodStaticUseBoth().m(); }
	}

	@DexEdit
	public static class MethodPrivateUseBoth {
		@DexAdd
		public void m() { p("public m"); }
		@DexAppend
		public static void main(String args[]) { new MethodPrivateUseBoth().m(); }
	}

}
