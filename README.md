`# Java Based Maths Interpreter

This is just small Maths Interpreter made as Practise.
Concepts from the book <a href="https://craftinginterpreters.com/">Crafting Interpreters</a> by **Robert Nystrom**

#What It Is:
## Interpreter
This is a simple interpreter in JAVA. It takes in a string of text and then iterates over it<br>
1) It searches for a DIGIT (0 - 9), once it finds one, it then keeps going forward looking for digits, OR, decimal point. If no decimal point is found, and a non digit character is found, it uses the already consumed characters and forms a number.<br>

<b>-></b> If a decimal point is seen while consuming a number, it's consumed and all digits found after the decimal point are consumed. If there is no digit after the decimal point, the decimal point is not consumed.

2) Next it has to interpret all operators. Expressions are parsed using Recursive Descent. It Starts with an expression, then according to low to high precendence, it descends. First, to equality, which will check for a '==' sign, then >, < and similar symbols are under the next precendence of comparision. Then we parse '+' and '-' signs. With the next precendence, we get factors, i.e. * and /. Further there is the '^' sign, then the unary operators, and finally literal numbers.
3) After parsing and while it's interpreting, it uses a visitor pattern. It can give results not only as numbers, but also as a false or true.

Ok so let us try this out!
Input : "10.5 + 10"
The parser first reaches the '1'. This 1 is 49 in ASCII code, which is in the range of digits '48' and '57'.
Now that we know the character is a digit, we then keep moving forward to find more digits. We find '0', so we consume it in the current string, or 'lexeme' as well. Next we see that the character is a decimal point. We do not consume it yet, we check if the character after the decimal point is a digit or not. If it is, we start moving ahead, consume the decimal point and all the following digits are the decimal part, which is five in our case.
Then it finds a ' ', or a \s or a space (ASCII 32). Spaces do not have any importance except for separating words or keywords or identifiers. So we skip that space.
Next we find  a +, this plus sign tells us we are going to be adding, and that we are going to be using a binary operator. It consumes the plus sign and records that we are going to be adding numbers.
Then, it moves to another ' ' or space. Again, we skip this because this has no importance.
Next, we meet a '1'. This one indicates a number, and we then check if the next digit is a number, if it is, which it is (i.e. it is 0), we then check the next, the next character is EOF. So our number is '10' and we finally know that. <br>

<b>ADD(NUMBER(10.5), NUMBER(10))</b>

And then we print the result.`