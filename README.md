# Simple Intepreter
This Java project implements an interpreter for a simple programming language that supports variable assignment, scoped operations, and variable printing.
## Languge features
### Variable assignment
- Assign a variable to some integer value, syntax `<name> = <integer value>`
- Assign a variable to some other variables value, syntax `<name> = <another_name>`
### Scope
- Variables and assignments are scoped within `scope { }` blocks.
- Any variables declared or reassigned inside a scope are discarded when the scope ends.
### Print
- Print the value of a variable, syntax `print <var_name>` (it can also print directly integer values, for example `print 12`)
- If the variable does not exist, print `null`.
## Example Program

```plaintext
x = 1
print x
scope {
  x = 2
  print x
  scope {
    x = 3
    y = x
    print x
    print y
  }
  print x
  print y
}
print x
```
### Expected output
```plaintext
1
2
3
3
2
null
1
```
## Grammar
```plaintext
nonterminals = {
