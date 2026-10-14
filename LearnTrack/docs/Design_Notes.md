Why you used ArrayList instead of array?
- Size is dynamic
- Easier to add and remove the object from arraylist
- Arraylist fits in in-memory CRUD operation very well

Where you used static members and why?
- We used static utility methods for Id generation and validations. 
- Because these methods doesn't need any object specific states 

Where you used inheritance and what you gained from it?
- Person is the base class holding the fields common to any person in the system: id, firstName, lastName, email, plus getDisplayName() and a toString().
- Student extends it and adds only what's student-specific: batch and the active flag, plus getStudentDetails() (which calls the inherited getDisplayName() and prints the extra fields) and its own toString() that calls super.toString().

Why: Person models "a human being tracked by the system," and Student is a specialization of that — an is-a relationship, which is exactly what class inheritance is for. It avoids duplicating id/firstName/lastName/email (and their getters/setters) in        
Student, and it reads like it was written to anticipate a second subtype — a Trainer extends Person sibling — that hasn't been added to src yet (there's a stray compiled Trainer.class under out/production/, suggesting one existed before but isn't in the     
current source).

Two other extends uses — exception hierarchy, not domain modeling

- EntityNotFoundException extends RuntimeException
- InvalidInputException extends RuntimeException

