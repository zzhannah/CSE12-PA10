/*=============================================================================
 Stack.java
 @author Gary Gillespie
 @version 02/17/2020
=============================================================================*/
public class Stack<Arbitrary extends Base> extends List<Arbitrary> {
        public Arbitrary pop () {
                return remove (END);
        }
        public Arbitrary push (Arbitrary element) {
                return insert (element, END);
        }
        public Arbitrary top () {
                return view (END);
        }
}