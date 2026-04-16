FUNCTION InfixToPostfix(infixString)
    CREATE empty Stack
    CREATE empty Result string
    
    FOR each character 'C' in infixString:
        IF 'C' is alphanumeric:
            ADD 'C' to Result
        ELSE IF 'C' is '(':
            PUSH 'C' to Stack
        ELSE IF 'C' is ')':
            WHILE Stack top is not '(':
                APPEND Stack.pop() to Result
            POP '(' from Stack
        ELSE IF 'C' is an Operator (+, -, *, /):
            WHILE Stack not empty AND precedence(Stack.top) >= precedence('C'):
                APPEND Stack.pop() to Result
            PUSH 'C' to Stack
            
    WHILE Stack is not empty:
        APPEND Stack.pop() to Result
    RETURN Result

FUNCTION InfixToPrefix(infixString)
    // Step 1: Reverse and Swap
    reversedString = REVERSE(infixString)
    modifiedString = REPLACE '(' with ')' and ')' with '(' in reversedString
    
    // Step 2: Use Postfix logic
    postfixResult = InfixToPostfix(modifiedString)
    
    // Step 3: Final reversal
    RETURN REVERSE(postfixResult)