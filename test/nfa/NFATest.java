package test.nfa;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import fa.nfa.NFA;

/**
 * Unit tests for the NFA
 *
 * @author CS361 Instructors, Jayce Lowry
 */
public class NFATest {
	
	private NFA nfa1() {
		NFA nfa = new NFA();
		
		nfa.addSigma('0');
		nfa.addSigma('1');
		
		assertTrue(nfa.addState("a"));
		assertTrue(nfa.setStart("a"));
		
		assertTrue(nfa.addState("b"));
		assertTrue(nfa.setFinal("b"));
		
		assertFalse(nfa.addState("a"));
		assertFalse(nfa.setStart("c"));
		assertFalse(nfa.setFinal("d"));
		
	
		assertTrue(nfa.addTransition("a", Set.of("a"), '0'));
		assertTrue(nfa.addTransition("a", Set.of("b"), '1'));
		assertTrue(nfa.addTransition("b", Set.of("a"), 'e'));
		
		assertFalse(nfa.addTransition("c", Set.of("a"), '0'));
		assertFalse(nfa.addTransition("a", Set.of("b"), '3'));
		assertFalse(nfa.addTransition("b", Set.of("d","c"), 'e'));
		
		
		
		return nfa;
		
	}

	@Test
	public void test1_1() {
		NFA nfa = nfa1();
		System.out.println("nfa1 instantiation done");
	}
	
	@Test
	public void test1_2() {
		NFA nfa = nfa1();
		assertNotNull(nfa.getState("a"));
		assertEquals(nfa.getState("a").getName(), "a");
		//ensures the same object
		assertEquals(nfa.getState("a"), nfa.getState("a"));
		assertTrue(nfa.isStart("a"));
		assertTrue(nfa.isFinal("b"));
		
		
		System.out.println("nfa1 correctness done");
	}
	
	@Test
	public void test1_3() {
		NFA nfa = nfa1();
		assertFalse(nfa.isDFA());
		System.out.println("nfa1 isDFA done");
	}
	
	@Test
	public void test1_4() {
		NFA nfa = nfa1();
		assertEquals(nfa.eClosure(nfa.getState("a")), Set.of(nfa.getState("a")));
		assertEquals(nfa.eClosure(nfa.getState("b")), Set.of(nfa.getState("a"), nfa.getState("b")));
		System.out.println("nfa1 eClosure done");
	}
	
	@Test
	public void test1_5() {
		NFA nfa = nfa1();
		assertFalse(nfa.accepts("0"));
		assertTrue(nfa.accepts("1"));
		assertFalse(nfa.accepts("00"));
		assertTrue(nfa.accepts("101"));
		assertFalse(nfa.accepts("e"));
		System.out.println("nfa1 accepts done");
	}
	
	@Test
	public void test1_6() {
		NFA nfa = nfa1();
		assertEquals(nfa.maxCopies("0"), 1);
		assertEquals(nfa.maxCopies("1"), 2);
		assertEquals(nfa.maxCopies("00"), 1);
		assertEquals(nfa.maxCopies("101"), 2);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("2"), 1);
		System.out.println("nfa1 maxCopies done");
	}
	
	private NFA nfa2() {
		NFA nfa = new NFA();
		
		nfa.addSigma('0');
		nfa.addSigma('1');
		
		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.addState("q1"));
		assertTrue(nfa.addState("q2"));
		assertTrue(nfa.addState("q3"));
		assertTrue(nfa.addState("q4"));
		assertTrue(nfa.setFinal("q3"));
		
		assertFalse(nfa.addState("q3"));
		assertFalse(nfa.setStart("q5"));
		assertFalse(nfa.setFinal("q6"));
		

		assertTrue(nfa.addTransition("q0", Set.of("q0"), '0'));
		assertTrue(nfa.addTransition("q0", Set.of("q0"), '1'));
		assertTrue(nfa.addTransition("q0", Set.of("q1"), '1'));
		assertTrue(nfa.addTransition("q1", Set.of("q2"), 'e'));
		assertTrue(nfa.addTransition("q2", Set.of("q4"), '0'));
		assertTrue(nfa.addTransition("q2", Set.of("q2","q3"), '1'));
		assertTrue(nfa.addTransition("q4", Set.of("q1"), '0'));
		
		assertFalse(nfa.addTransition("q0", Set.of("q5"), '0'));
		assertFalse(nfa.addTransition("q0", Set.of("q3"), '3'));
		assertFalse(nfa.addTransition("q5", Set.of("q0","q2"), 'e'));
		
		
		
		return nfa;
		
	}
	
	@Test
	public void test2_1() {
		NFA nfa = nfa2();
		System.out.println("nfa2 instantiation done");
	}
	
	@Test
	public void test2_2() {
		NFA nfa = nfa2();
		assertNotNull(nfa.getState("q0"));
		assertEquals(nfa.getState("q3").getName(), "q3");
		assertNull(nfa.getState("q5"));
		//ensures the same object
		assertEquals(nfa.getState("q2"), nfa.getState("q2"));
		assertTrue(nfa.isStart("q0"));
		assertFalse(nfa.isStart("q3"));
		assertTrue(nfa.isFinal("q3"));
		assertFalse(nfa.isFinal("q6"));
		
		System.out.println("nfa2 correctness done");
	}
	
	@Test
	public void test2_3() {
		NFA nfa = nfa2();
		assertFalse(nfa.isDFA());
		System.out.println("nfa2 isDFA done");
	}
	
	@Test
	public void test2_4() {
		NFA nfa = nfa2();
		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));
		assertEquals(nfa.eClosure(nfa.getState("q1")), Set.of(nfa.getState("q1"),nfa.getState("q2")));
		assertEquals(nfa.eClosure(nfa.getState("q3")), Set.of(nfa.getState("q3")));
		assertEquals(nfa.eClosure(nfa.getState("q4")), Set.of(nfa.getState("q4")));
		
		System.out.println("nfa2 eClosure done");
	}
	
	@Test
	public void test2_5() {
		NFA nfa = nfa2();
		assertTrue(nfa.accepts("1111"));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("0001100"));
		assertTrue(nfa.accepts("010011"));
		assertFalse(nfa.accepts("0101"));
		System.out.println("nfa2 accepts done");
	}
	
	@Test
	public void test2_6() {
		NFA nfa = nfa2();
		assertEquals(nfa.maxCopies("1111"), 4);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("0001100"), 4);
		assertEquals(nfa.maxCopies("010011"), 4);
		assertEquals(nfa.maxCopies("0101"), 3);
		
		System.out.println("nfa2 maxCopies done");
	}
	
	private NFA nfa3() {
		NFA nfa = new NFA();
		
		nfa.addSigma('#');
		nfa.addSigma('0');
		nfa.addSigma('1');
		
		assertTrue(nfa.addState("W"));
		assertTrue(nfa.setStart("W"));
		assertTrue(nfa.addState("L"));
		assertTrue(nfa.addState("I"));
		assertTrue(nfa.addState("N"));
		assertTrue(nfa.setFinal("N"));
		
		assertFalse(nfa.addState("N"));
		assertFalse(nfa.setStart("Z"));
		assertFalse(nfa.setFinal("Y"));

		assertTrue(nfa.addTransition("W", Set.of("N"), '#'));
		assertTrue(nfa.addTransition("W", Set.of("L"), 'e'));
		
		assertTrue(nfa.addTransition("L", Set.of("L","N"), '0'));
		assertTrue(nfa.addTransition("L", Set.of("I"), 'e'));
		
		assertTrue(nfa.addTransition("I", Set.of("I"), '1'));
		assertTrue(nfa.addTransition("I", Set.of("N"), '1'));
		
		assertTrue(nfa.addTransition("N", Set.of("W"), '#'));
		
		assertFalse(nfa.addTransition("W", Set.of("K"), '0'));
		assertFalse(nfa.addTransition("W", Set.of("W"), '3'));
		assertFalse(nfa.addTransition("ZZ", Set.of("W","Z"), 'e'));
		
		
		return nfa;
		
	}

	@Test
	public void test3_1() {
		NFA nfa = nfa3();
		System.out.println("nfa3 instantiation done");
	}
	
	@Test
	public void test3_2() {
		NFA nfa = nfa3();
		assertNotNull(nfa.getState("W"));
		assertEquals(nfa.getState("N").getName(), "N");
		assertNull(nfa.getState("Z0"));
//		assertEquals(nfa.getState("I").toStates('1'), Set.of(nfa.getState("I"), nfa.getState("N")));
		assertTrue(nfa.isStart("W"));
		assertFalse(nfa.isStart("L"));
		assertTrue(nfa.isFinal("N"));
		assertFalse(nfa.isFinal("I"));
		System.out.println("nfa3 correctness done");
	}
	
	@Test
	public void test3_3() {
		NFA nfa = nfa3();
		assertFalse(nfa.isDFA());
		System.out.println("nfa3 isDFA done");
	}
	
	@Test
	public void test3_4() {
		NFA nfa = nfa3();
		assertEquals(nfa.eClosure(nfa.getState("W")), Set.of(nfa.getState("W"),nfa.getState("L"),nfa.getState("I")));
		assertEquals(nfa.eClosure(nfa.getState("N")), Set.of(nfa.getState("N")));
		assertEquals(nfa.eClosure(nfa.getState("L")), Set.of(nfa.getState("L"),nfa.getState("I")));
		assertEquals(nfa.eClosure(nfa.getState("I")), Set.of(nfa.getState("I")));
		
		System.out.println("nfa3 eClosure done");
	}
	
	@Test
	public void test3_5() {
		NFA nfa = nfa3();
		assertTrue(nfa.accepts("###"));
		assertTrue(nfa.accepts("111#00"));
		assertTrue(nfa.accepts("01#11##"));
		assertFalse(nfa.accepts("#01000###"));
		assertFalse(nfa.accepts("011#00010#"));
		System.out.println("nfa3 accepts done");
	}
	
	@Test
	public void test3_6() {
		NFA nfa = nfa3();
		assertEquals(nfa.maxCopies("###"), 3);
		assertEquals(nfa.maxCopies("e"), 3);
		assertEquals(nfa.maxCopies("011#00010#"), 3);
		assertEquals(nfa.maxCopies("23"), 3);
		assertEquals(nfa.maxCopies("011#00010#"), 3);
		System.out.println("nfa3 maxCopies done");
	}

	private NFA nfa4() {
		NFA nfa = new NFA();
		nfa.addSigma('0');
		nfa.addSigma('1');
		nfa.addSigma('e');

		assertTrue(nfa.addState("a"));
		assertTrue(nfa.addState("b"));

		assertTrue(nfa.setStart("a"));
		assertTrue(nfa.setFinal("b"));

		assertFalse(nfa.addState("b"));
		assertFalse(nfa.setStart("x"));
		assertFalse(nfa.setFinal("y"));

		assertTrue(nfa.addTransition("a", Set.of("a"), '0'));
		assertTrue(nfa.addTransition("a", Set.of("b"), '1'));
		assertTrue(nfa.addTransition("b", Set.of("a"), '0'));
		assertTrue(nfa.addTransition("b", Set.of("b"), '1'));

		assertFalse(nfa.addTransition("x", Set.of("a"), 'e'));
		assertFalse(nfa.addTransition("a", Set.of("y"), 'e'));
		assertFalse(nfa.addTransition("a", Set.of("b"), '3'));

		return nfa;
	}

	@Test
	public void test4_1() {
		NFA nfa = nfa4();
		System.out.println("nfa4 instantiation done");
	}

	@Test
	public void test4_2() {
		NFA nfa = nfa4();
		assertNotNull(nfa.getState("a"));
		assertEquals(nfa.getState("a").getName(), "a");
		assertEquals(nfa.getState("b"), nfa.getState("b"));

		assertTrue(nfa.isStart("a"));
		assertTrue(nfa.isFinal("b"));

		assertNull(nfa.getState("x"));
		assertFalse(nfa.isStart("b"));
		assertFalse(nfa.isFinal("a"));
		assertFalse(nfa.getSigma().contains('e'));

		System.out.println("nfa4 correctness done");
	}

	@Test
	public void test4_3() {
		NFA nfa = nfa4();
		assertTrue(nfa.isDFA());
		System.out.println("nfa4 isDFA done");
	}

	@Test
	public void test4_4() {
		NFA nfa = nfa4();
		assertEquals(nfa.eClosure(nfa.getState("a")), Set.of(nfa.getState("a")));
		assertEquals(nfa.eClosure(nfa.getState("b")), Set.of(nfa.getState("b")));
		System.out.println("nfa4 eClosure done");
	}

	@Test
	public void test4_5() {
		NFA nfa = nfa4();

		assertFalse(nfa.accepts("0"));
		assertTrue(nfa.accepts("1"));
		assertFalse(nfa.accepts("00"));
		assertTrue(nfa.accepts("101"));
		assertFalse(nfa.accepts("e"));

		System.out.println("nfa4 accepts done");
	}

	@Test
	public void test4_6() {
		NFA nfa = nfa4();

		assertEquals(nfa.maxCopies("0"), 1);
		assertEquals(nfa.maxCopies("1"), 1);
		assertEquals(nfa.maxCopies("00"), 1);
		assertEquals(nfa.maxCopies("101"), 1);
		assertEquals(nfa.maxCopies("2"), 1);

		System.out.println("nfa4 maxCopies done");
	}

	private NFA nfa5() {
		NFA nfa = new NFA();

		nfa.addSigma('e');

		assertTrue(nfa.addState("a"));
		assertTrue(nfa.addState("b"));
		assertTrue(nfa.addState("c"));

		assertTrue(nfa.setStart("a"));
		assertTrue(nfa.setFinal("c"));

		assertFalse(nfa.addState("c"));
		assertFalse(nfa.setFinal("x"));
		assertFalse(nfa.setStart("x"));

		assertTrue(nfa.addTransition("a", Set.of("b"), 'e'));
		assertTrue(nfa.addTransition("b", Set.of("c"), 'e'));

		assertFalse(nfa.addTransition("a", Set.of("a"), '0'));
		assertFalse(nfa.addTransition("a", Set.of("x"), 'e'));

		return nfa;
	}

	@Test
	public void test5_1() {
		NFA nfa = nfa5();
		System.out.println("nfa5 instantiation done");
	}

	@Test
	public void test5_2() {
		NFA nfa = nfa5();

		assertNotNull(nfa.getState("a"));
		assertNotNull(nfa.getState("b"));
		assertNotNull(nfa.getState("c"));
		assertEquals(nfa.getState("c").getName(), "c");
		assertEquals(nfa.getState("b"), nfa.getState("b"));

		assertTrue(nfa.isStart("a"));
		assertTrue(nfa.isFinal("c"));

		assertNull(nfa.getState("x"));
		assertFalse(nfa.isStart("b"));
		assertFalse(nfa.isFinal("a"));
		assertFalse(nfa.getSigma().contains('e'));

		System.out.println("nfa5 correctness done");
	}

	@Test
	public void test5_3() {
		NFA nfa = nfa5();
		assertFalse(nfa.isDFA());
		System.out.println("nfa5 isDFA done");
	}

	@Test
	public void test5_4() {
		NFA nfa = nfa5();
		assertEquals(nfa.eClosure(nfa.getState("a")), Set.of(nfa.getState("a"), nfa.getState("b"), nfa.getState("c")));
		assertEquals(nfa.eClosure(nfa.getState("b")), Set.of(nfa.getState("b"), nfa.getState("c")));
		assertEquals(nfa.eClosure(nfa.getState("c")), Set.of(nfa.getState("c")));
		System.out.println("nfa5 eClosure done");
	}

	@Test
	public void test5_5() {
		NFA nfa = nfa5();

		assertTrue(nfa.accepts(""));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ba"));
		assertTrue(nfa.accepts("e"));

		System.out.println("nfa5 accepts done");
	}

	@Test
	public void test5_6() {
		NFA nfa = nfa5();

		assertEquals(nfa.maxCopies(""), 3);
		assertEquals(nfa.maxCopies("a"), 3);
		assertEquals(nfa.maxCopies("ab"), 3);
		assertEquals(nfa.maxCopies("b"), 3);
		assertEquals(nfa.maxCopies("ba"), 3);
		assertEquals(nfa.maxCopies("e"), 3);

		System.out.println("nfa5 maxCopies done");
	}

	private NFA nfa6() {
		NFA nfa = new NFA();

		nfa.addSigma('?');
		nfa.addSigma('%');
		nfa.addSigma('$');

		assertTrue(nfa.addState("R"));
		assertTrue(nfa.addState("S"));
		assertTrue(nfa.addState("T"));
		assertTrue(nfa.addState("U"));

		assertFalse(nfa.addState(null));

		assertTrue(nfa.setStart("R"));
		assertTrue(nfa.setFinal("S"));
		assertTrue(nfa.setFinal("U"));

		assertFalse(nfa.setStart(null));
		assertFalse(nfa.setFinal(null));

		assertTrue(nfa.addTransition("R", Set.of("R", "T"), '?'));
		assertTrue(nfa.addTransition("R", Set.of("S"), '$'));
		assertTrue(nfa.addTransition("R", Set.of("U"), 'e'));
		assertTrue(nfa.addTransition("S", Set.of("U"), '%'));
		assertTrue(nfa.addTransition("S", Set.of("R"), '$'));
		assertTrue(nfa.addTransition("T", Set.of("R"), '%'));
		assertTrue(nfa.addTransition("U", Set.of("R"), 'e'));

		return nfa;
	}

	@Test
	public void test6_1() {
		NFA nfa = nfa6();
		System.out.println("nfa6 instantiation done");
	}

	@Test
	public void test6_2() {
		NFA nfa = nfa6();

		assertNull(nfa.getState(null));

		assertNotNull(nfa.getState("R"));
		assertNotNull(nfa.getState("S"));
		assertNotNull(nfa.getState("T"));
		assertNotNull(nfa.getState("U"));

		assertEquals(nfa.getState("R").getName(), "R");
		assertEquals(nfa.getState("U"), nfa.getState("U"));

		assertTrue(nfa.isStart("R"));
		assertTrue(nfa.isFinal("S"));
		assertTrue(nfa.isFinal("U"));

		System.out.println("nfa6 correctness done");
	}

	@Test
	public void test6_3() {
		NFA nfa = nfa6();
		assertFalse(nfa.isDFA());
		System.out.println("nfa6 isDFA done");
	}

	@Test
	public void test6_4() {
		NFA nfa = nfa6();

		assertEquals(nfa.eClosure(nfa.getState("R")), Set.of(nfa.getState("R"), nfa.getState("U")));
		assertEquals(nfa.eClosure(nfa.getState("S")), Set.of(nfa.getState("S")));
		assertEquals(nfa.eClosure(nfa.getState("T")), Set.of(nfa.getState("T")));
		assertEquals(nfa.eClosure(nfa.getState("U")), Set.of(nfa.getState("R"), nfa.getState("U")));

		System.out.println("nfa6 eClosure done");
	}

	@Test
	public void test6_5() {
		NFA nfa = nfa6();

		assertTrue(nfa.accepts(""));
		assertTrue(nfa.accepts("?"));
		assertTrue(nfa.accepts("$"));
		assertTrue(nfa.accepts("?%"));
		assertTrue(nfa.accepts("$%"));
		assertTrue(nfa.accepts("$%??"));
		assertTrue(nfa.accepts("$$?"));
		assertFalse(nfa.accepts("%"));
		assertFalse(nfa.accepts("%%"));
		assertFalse(nfa.accepts("$?"));
		assertFalse(nfa.accepts(null));

		System.out.println("nfa6 accepts done");
	}

	@Test
	public void test6_6() {
		NFA nfa = nfa6();

		assertEquals(nfa.maxCopies(""), 2);
		assertEquals(nfa.maxCopies("?"), 3);
		assertEquals(nfa.maxCopies("$"), 2);
		assertEquals(nfa.maxCopies("?%"), 3);
		assertEquals(nfa.maxCopies("$%"), 2);
		assertEquals(nfa.maxCopies("$%??"), 3);
		assertEquals(nfa.maxCopies("$$?"), 3);
		assertEquals(nfa.maxCopies("%"), 2);
		assertEquals(nfa.maxCopies("%%"), 2);
		assertEquals(nfa.maxCopies("$?"), 2);

		System.out.println("nfa6 maxCopies done");
	}

	private NFA nfa7() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');
		assertTrue(nfa.addState("q0"));

		return nfa;
	}

	@Test
	public void test7_1() {
		NFA nfa = nfa7();
		System.out.println("nfa7 instantiation done");
	}

	@Test
	public void test7_2() {
		NFA nfa = nfa7();

		assertNotNull(nfa.getState("q0"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertFalse(nfa.isStart("q0"));
		assertFalse(nfa.isFinal("q0"));

		System.out.println("nfa7 correctness done");
	}

	@Test
	public void test7_3() {
		NFA nfa = nfa7();
		assertTrue(nfa.isDFA());
		System.out.println("nfa7 isDFA done");
	}

	@Test
	public void test7_4() {
		NFA nfa = nfa7();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));

		System.out.println("nfa7 eClosure done");
	}

	@Test
	public void test7_5() {
		NFA nfa = nfa7();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));

		System.out.println("nfa7 accepts done");
	}

	@Test
	public void test7_6() {
		NFA nfa = nfa7();

		assertEquals(nfa.maxCopies(""), 0);
		assertEquals(nfa.maxCopies("e"), 0);
		assertEquals(nfa.maxCopies("a"), 0);
		assertEquals(nfa.maxCopies("b"), 0);
		assertEquals(nfa.maxCopies("ab"), 0);
		assertEquals(nfa.maxCopies("ba"), 0);

		System.out.println("nfa7 maxCopies done");
	}

	private NFA nfa8() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.setStart("q0"));

		return nfa;
	}

	@Test
	public void test8_1() {
		NFA nfa = nfa8();
		System.out.println("nfa8 instantiation done");
	}

	@Test
	public void test8_2() {
		NFA nfa = nfa8();

		assertNotNull(nfa.getState("q0"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertTrue(nfa.isStart("q0"));
		assertFalse(nfa.isFinal("q0"));

		System.out.println("nfa8 correctness done");
	}

	@Test
	public void test8_3() {
		NFA nfa = nfa8();
		assertTrue(nfa.isDFA());
		System.out.println("nfa8 isDFA done");
	}

	@Test
	public void test8_4() {
		NFA nfa = nfa8();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));

		System.out.println("nfa8 eClosure done");
	}

	@Test
	public void test8_5() {
		NFA nfa = nfa8();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));

		System.out.println("nfa8 accepts done");
	}

	@Test
	public void test8_6() {
		NFA nfa = nfa8();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 1);
		assertEquals(nfa.maxCopies("b"), 1);
		assertEquals(nfa.maxCopies("ab"), 1);
		assertEquals(nfa.maxCopies("ba"), 1);

		System.out.println("nfa8 maxCopies done");
	}

	private NFA nfa9() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.setFinal("q0"));

		return nfa;
	}

	@Test
	public void test9_1() {
		NFA nfa = nfa9();
		System.out.println("nfa9 instantiation done");
	}

	@Test
	public void test9_2() {
		NFA nfa = nfa9();

		assertNotNull(nfa.getState("q0"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertTrue(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q0"));

		System.out.println("nfa9 correctness done");
	}

	@Test
	public void test9_3() {
		NFA nfa = nfa9();
		assertTrue(nfa.isDFA());
		System.out.println("nfa9 isDFA done");
	}

	@Test
	public void test9_4() {
		NFA nfa = nfa9();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));

		System.out.println("nfa9 eClosure done");
	}

	@Test
	public void test9_5() {
		NFA nfa = nfa9();

		assertTrue(nfa.accepts(""));
		assertTrue(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));

		System.out.println("nfa8 accepts done");
	}

	@Test
	public void test9_6() {
		NFA nfa = nfa9();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 1);
		assertEquals(nfa.maxCopies("b"), 1);
		assertEquals(nfa.maxCopies("ab"), 1);
		assertEquals(nfa.maxCopies("ba"), 1);

		System.out.println("nfa9 maxCopies done");
	}

	private NFA nfa10() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.setFinal("q0"));

		return nfa;
	}

	@Test
	public void test10_1() {
		NFA nfa = nfa10();
		System.out.println("nfa10 instantiation done");
	}

	@Test
	public void test10_2() {
		NFA nfa = nfa10();

		assertNotNull(nfa.getState("q0"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertFalse(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q0"));

		System.out.println("nfa10 correctness done");
	}

	@Test
	public void test10_3() {
		NFA nfa = nfa10();
		assertTrue(nfa.isDFA());
		System.out.println("nfa10 isDFA done");
	}

	@Test
	public void test10_4() {
		NFA nfa = nfa10();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));

		System.out.println("nfa10 eClosure done");
	}

	@Test
	public void test10_5() {
		NFA nfa = nfa10();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));

		System.out.println("nfa10 accepts done");
	}

	@Test
	public void test10_6() {
		NFA nfa = nfa10();

		assertEquals(nfa.maxCopies(""), 0);
		assertEquals(nfa.maxCopies("e"), 0);
		assertEquals(nfa.maxCopies("a"), 0);
		assertEquals(nfa.maxCopies("b"), 0);
		assertEquals(nfa.maxCopies("ab"), 0);
		assertEquals(nfa.maxCopies("ba"), 0);

		System.out.println("nfa10 maxCopies done");
	}

	private NFA nfa11() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.setFinal("q0"));

		assertTrue(nfa.addTransition("q0", Set.of("q0"), 'a'));

		return nfa;
	}

	@Test
	public void test11_1() {
		NFA nfa = nfa11();
		System.out.println("nfa11 instantiation done");
	}

	@Test
	public void test11_2() {
		NFA nfa = nfa11();

		assertNotNull(nfa.getState("q0"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertTrue(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q0"));

		System.out.println("nfa11 correctness done");
	}

	@Test
	public void test11_3() {
		NFA nfa = nfa11();
		assertTrue(nfa.isDFA());
		System.out.println("nfa11 isDFA done");
	}

	@Test
	public void test11_4() {
		NFA nfa = nfa11();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));

		System.out.println("nfa11 eClosure done");
	}

	@Test
	public void test11_5() {
		NFA nfa = nfa11();

		assertTrue(nfa.accepts(""));
		assertTrue(nfa.accepts("e"));
		assertTrue(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));
		assertTrue(nfa.accepts("aaa"));

		System.out.println("nfa11 accepts done");
	}

	@Test
	public void test11_6() {
		NFA nfa = nfa11();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 1);
		assertEquals(nfa.maxCopies("b"), 1);
		assertEquals(nfa.maxCopies("ab"), 1);
		assertEquals(nfa.maxCopies("ba"), 1);
		assertEquals(nfa.maxCopies("aaa"), 1);

		System.out.println("nfa11 maxCopies done");
	}

	private NFA nfa12() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.setStart("q0"));

		assertTrue(nfa.addTransition("q0", Set.of("q0"), 'a'));

		return nfa;
	}

	@Test
	public void test12_1() {
		NFA nfa = nfa12();
		System.out.println("nfa12 instantiation done");
	}

	@Test
	public void test12_2() {
		NFA nfa = nfa12();

		assertNotNull(nfa.getState("q0"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertTrue(nfa.isStart("q0"));
		assertFalse(nfa.isFinal("q0"));

		System.out.println("nfa12 correctness done");
	}

	@Test
	public void test12_3() {
		NFA nfa = nfa12();
		assertTrue(nfa.isDFA());
		System.out.println("nfa12 isDFA done");
	}

	@Test
	public void test12_4() {
		NFA nfa = nfa12();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));

		System.out.println("nfa12 eClosure done");
	}

	@Test
	public void test12_5() {
		NFA nfa = nfa12();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));
		assertFalse(nfa.accepts("aaa"));

		System.out.println("nfa12 accepts done");
	}

	@Test
	public void test12_6() {
		NFA nfa = nfa12();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 1);
		assertEquals(nfa.maxCopies("b"), 1);
		assertEquals(nfa.maxCopies("ab"), 1);
		assertEquals(nfa.maxCopies("ba"), 1);
		assertEquals(nfa.maxCopies("aaa"), 1);

		System.out.println("nfa11 maxCopies done");
	}

	private NFA nfa13() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.addState("q1"));
		assertTrue(nfa.addState("q2"));

		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.setFinal("q2"));

		assertTrue(nfa.addTransition("q0", Set.of("q0"), 'a'));
		assertTrue(nfa.addTransition("q0", Set.of("q1"), 'e'));
		assertTrue(nfa.addTransition("q1", Set.of("q2"), 'e'));
		assertTrue(nfa.addTransition("q2", Set.of("q2"), 'b'));
		assertTrue(nfa.addTransition("q2", Set.of("q0"), 'e'));

		return nfa;
	}

	@Test
	public void test13_1() {
		NFA nfa = nfa13();
		System.out.println("nfa13 instantiation done");
	}

	@Test
	public void test13_2() {
		NFA nfa = nfa13();

		assertNotNull(nfa.getState("q0"));
		assertNotNull(nfa.getState("q1"));
		assertNotNull(nfa.getState("q2"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q2"), nfa.getState("q2"));

		assertTrue(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q2"));

		System.out.println("nfa13 correctness done");
	}

	@Test
	public void test13_3() {
		NFA nfa = nfa13();
		assertFalse(nfa.isDFA());
		System.out.println("nfa13 isDFA done");
	}

	@Test
	public void test13_4() {
		NFA nfa = nfa13();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0"), nfa.getState("q1"), nfa.getState("q2")));
		assertEquals(nfa.eClosure(nfa.getState("q1")), Set.of(nfa.getState("q0"), nfa.getState("q1"), nfa.getState("q2")));
		assertEquals(nfa.eClosure(nfa.getState("q2")), Set.of(nfa.getState("q0"), nfa.getState("q1"), nfa.getState("q2")));

		System.out.println("nfa13 eClosure done");
	}

	@Test
	public void test13_5() {
		NFA nfa = nfa13();

		assertTrue(nfa.accepts(""));
		assertTrue(nfa.accepts("e"));
		assertTrue(nfa.accepts("a"));
		assertTrue(nfa.accepts("b"));
		assertTrue(nfa.accepts("ab"));
		assertTrue(nfa.accepts("bb"));
		assertTrue(nfa.accepts("aaa"));

		System.out.println("nfa13 accepts done");
	}

	@Test
	public void test13_6() {
		NFA nfa = nfa13();

		assertEquals(nfa.maxCopies(""), 3);
		assertEquals(nfa.maxCopies("e"), 3);
		assertEquals(nfa.maxCopies("a"), 3);
		assertEquals(nfa.maxCopies("b"), 3);
		assertEquals(nfa.maxCopies("ab"), 3);
		assertEquals(nfa.maxCopies("ba"), 3);
		assertEquals(nfa.maxCopies("aaa"), 3);

		System.out.println("nfa13 maxCopies done");
	}

	private NFA nfa14() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.addState("q1"));
		assertTrue(nfa.addState("q2"));

		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.setFinal("q2"));

		assertTrue(nfa.addTransition("q0", Set.of("q1"), 'a'));
		assertTrue(nfa.addTransition("q0", Set.of("q2"), 'a'));
		assertTrue(nfa.addTransition("q1", Set.of("q1"), 'a'));
		assertTrue(nfa.addTransition("q2", Set.of("q2"), 'b'));

		return nfa;
	}

	@Test
	public void test14_1() {
		NFA nfa = nfa14();
		System.out.println("nfa14 instantiation done");
	}

	@Test
	public void test14_2() {
		NFA nfa = nfa14();

		assertNotNull(nfa.getState("q0"));
		assertNotNull(nfa.getState("q1"));
		assertNotNull(nfa.getState("q2"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q2"), nfa.getState("q2"));

		assertTrue(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q2"));

		System.out.println("nfa14 correctness done");
	}

	@Test
	public void test14_3() {
		NFA nfa = nfa14();
		assertFalse(nfa.isDFA());
		System.out.println("nfa13 isDFA done");
	}

	@Test
	public void test14_4() {
		NFA nfa = nfa14();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));
		assertEquals(nfa.eClosure(nfa.getState("q1")), Set.of(nfa.getState("q1")));
		assertEquals(nfa.eClosure(nfa.getState("q2")), Set.of(nfa.getState("q2")));

		System.out.println("nfa14 eClosure done");
	}

	@Test
	public void test14_5() {
		NFA nfa = nfa14();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertTrue(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertTrue(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));
		assertFalse(nfa.accepts("aaa"));
		assertFalse(nfa.accepts("aab"));

		System.out.println("nfa14 accepts done");
	}

	@Test
	public void test14_6() {
		NFA nfa = nfa14();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 2);
		assertEquals(nfa.maxCopies("b"), 1);
		assertEquals(nfa.maxCopies("ab"), 2);
		assertEquals(nfa.maxCopies("ba"), 1);
		assertEquals(nfa.maxCopies("aaa"), 2);
		assertEquals(nfa.maxCopies("aab"), 2);

		System.out.println("nfa14 maxCopies done");
	}

	private NFA nfa15() {
		NFA nfa = new NFA();

		nfa.addSigma('a');
		nfa.addSigma('b');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.addState("q1"));
		assertTrue(nfa.addState("q2"));
		assertTrue(nfa.addState("q3"));
		assertTrue(nfa.addState("q4"));

		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.setFinal("q4"));

		assertTrue(nfa.addTransition("q0", Set.of("q1"), 'a'));
		assertTrue(nfa.addTransition("q1", Set.of("q2"), 'b'));
		assertTrue(nfa.addTransition("q2", Set.of("q3"), 'b'));
		assertTrue(nfa.addTransition("q3", Set.of("q4"), 'a'));
		assertTrue(nfa.addTransition("q4", Set.of("q0"), 'e'));

		return nfa;
	}

	@Test
	public void test15_1() {
		NFA nfa = nfa15();
		System.out.println("nfa15 instantiation done");
	}

	@Test
	public void test15_2() {
		NFA nfa = nfa15();

		assertNotNull(nfa.getState("q0"));
		assertNotNull(nfa.getState("q1"));
		assertNotNull(nfa.getState("q2"));
		assertNotNull(nfa.getState("q3"));
		assertNotNull(nfa.getState("q4"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q3"), nfa.getState("q3"));

		assertTrue(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q4"));

		System.out.println("nfa14 correctness done");
	}

	@Test
	public void test15_3() {
		NFA nfa = nfa15();
		assertFalse(nfa.isDFA());
		System.out.println("nfa15 isDFA done");
	}

	@Test
	public void test15_4() {
		NFA nfa = nfa15();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));
		assertEquals(nfa.eClosure(nfa.getState("q1")), Set.of(nfa.getState("q1")));
		assertEquals(nfa.eClosure(nfa.getState("q2")), Set.of(nfa.getState("q2")));
		assertEquals(nfa.eClosure(nfa.getState("q3")), Set.of(nfa.getState("q3")));
		assertEquals(nfa.eClosure(nfa.getState("q4")), Set.of(nfa.getState("q0"), nfa.getState("q4")));

		System.out.println("nfa14 eClosure done");
	}

	@Test
	public void test15_5() {
		NFA nfa = nfa15();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("b"));
		assertFalse(nfa.accepts("ab"));
		assertFalse(nfa.accepts("bb"));
		assertFalse(nfa.accepts("aaa"));
		assertFalse(nfa.accepts("aab"));
		assertFalse(nfa.accepts("abbaa"));
		assertTrue(nfa.accepts("abba"));
		assertTrue(nfa.accepts("abbaabba"));

		System.out.println("nfa15 accepts done");
	}

	@Test
	public void test15_6() {
		NFA nfa = nfa15();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 1);
		assertEquals(nfa.maxCopies("b"), 1);
		assertEquals(nfa.maxCopies("ab"), 1);
		assertEquals(nfa.maxCopies("ba"), 1);
		assertEquals(nfa.maxCopies("aaa"), 1);
		assertEquals(nfa.maxCopies("aab"), 1);
		assertEquals(nfa.maxCopies("abbaa"), 2);
		assertEquals(nfa.maxCopies("abba"), 2);
		assertEquals(nfa.maxCopies("abbaabba"), 2);

		System.out.println("nfa15 maxCopies done");
	}

	private NFA nfa16() {
		NFA nfa = new NFA();

		nfa.addSigma('a');

		assertTrue(nfa.addState("q0"));
		assertTrue(nfa.addState("q1"));

		assertTrue(nfa.setStart("q0"));
		assertTrue(nfa.setFinal("q1"));

		assertTrue(nfa.addTransition("q0", Set.of("q0"), 'a'));

		return nfa;
	}

	@Test
	public void test16_1() {
		NFA nfa = nfa16();
		System.out.println("nfa16 instantiation done");
	}

	@Test
	public void test16_2() {
		NFA nfa = nfa16();

		assertNotNull(nfa.getState("q0"));
		assertNotNull(nfa.getState("q1"));

		assertEquals(nfa.getState("q0").getName(), "q0");
		assertEquals(nfa.getState("q0"), nfa.getState("q0"));

		assertTrue(nfa.isStart("q0"));
		assertTrue(nfa.isFinal("q1"));

		System.out.println("nfa16 correctness done");
	}

	@Test
	public void test16_3() {
		NFA nfa = nfa16();
		assertTrue(nfa.isDFA());
		System.out.println("nfa15 isDFA done");
	}

	@Test
	public void test16_4() {
		NFA nfa = nfa16();

		assertEquals(nfa.eClosure(nfa.getState("q0")), Set.of(nfa.getState("q0")));
		assertEquals(nfa.eClosure(nfa.getState("q1")), Set.of(nfa.getState("q1")));

		System.out.println("nfa16 eClosure done");
	}

	@Test
	public void test16_5() {
		NFA nfa = nfa16();

		assertFalse(nfa.accepts(""));
		assertFalse(nfa.accepts("e"));
		assertFalse(nfa.accepts("a"));
		assertFalse(nfa.accepts("aa"));
		assertFalse(nfa.accepts("aaa"));

		System.out.println("nfa16 accepts done");
	}

	@Test
	public void test16_6() {
		NFA nfa = nfa16();

		assertEquals(nfa.maxCopies(""), 1);
		assertEquals(nfa.maxCopies("e"), 1);
		assertEquals(nfa.maxCopies("a"), 1);
		assertEquals(nfa.maxCopies("aa"), 1);
		assertEquals(nfa.maxCopies("aaa"), 1);

		System.out.println("nfa16 maxCopies done");
	}
}
