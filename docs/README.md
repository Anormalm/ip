# Mochizuki User Guide

Konbanwa!  こんばんは！

Mochizuki is a lightweight task tracker with a calm, moonlit personality. It supports to-dos, deadlines, and events, plus fast searching and date queries.

![Screenshot of Mochizuki](images/mochizuki.png)

## Quick Start

1. Ensure you have Java 17 installed.
2. Run the app with:
   ```
   java -jar Mochizuki.jar
   ```
3. Type a command and press Enter.

## Command Summary

- `list`
- `todo DESCRIPTION`
- `deadline DESCRIPTION /by YYYY-MM-DD`
- `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD`
- `mark INDEX`
- `unmark INDEX`
- `delete INDEX`
- `find KEYWORD`
- `find-date YYYY-MM-DD`
- `bye`

## Features

### Add a to-do

Adds a simple task without dates.

Example: `todo borrow book`

Expected output:
```
 Got it. I've added this task:
   [T][ ] borrow book
 Now you have 1 tasks in the list.
```

### Add a deadline

Adds a task with a due date. Dates must be in `YYYY-MM-DD`.

Example: `deadline return book /by 2026-02-05`

Expected output:
```
 Got it. I've added this task:
   [D][ ] return book (by: Feb 5 2026)
 Now you have 2 tasks in the list.
```

### Add an event

Adds a task with a start and end date. Dates must be in `YYYY-MM-DD`.

Example: `event project meeting /from 2026-02-01 /to 2026-02-10`

Expected output:
```
 Got it. I've added this task:
   [E][ ] project meeting (from: Feb 1 2026 to: Feb 10 2026)
 Now you have 3 tasks in the list.
```

### List tasks

Shows all tasks in the current list.

Example: `list`

Expected output:
```
 Here are the tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] return book (by: Feb 5 2026)
 3.[E][ ] project meeting (from: Feb 1 2026 to: Feb 10 2026)
```

### Mark a task as done

Example: `mark 2`

Expected output:
```
 Nice! I've marked this task as done:
   [D][X] return book (by: Feb 5 2026)
```

### Unmark a task

Example: `unmark 2`

Expected output:
```
 OK, I've marked this task as not done yet:
   [D][ ] return book (by: Feb 5 2026)
```

### Delete a task

Example: `delete 3`

Expected output:
```
 Noted. I've removed this task:
   [E][ ] project meeting (from: Feb 1 2026 to: Feb 10 2026)
 Now you have 2 tasks in the list.
```

### Find tasks by keyword

Finds tasks whose descriptions contain the keyword (case-insensitive).

Example: `find book`

Expected output:
```
 Here are the matching tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] return book (by: Feb 5 2026)
```

### Find tasks by date

Shows deadlines on the date and events spanning the date.

Example: `find-date 2026-02-05`

Expected output:
```
 Tasks on Feb 5 2026:
 1.[D][ ] return book (by: Feb 5 2026)
 2.[E][ ] project meeting (from: Feb 1 2026 to: Feb 10 2026)
```

### Exit

Example: `bye`

Expected output:
```
 Bye. Hope to see you again soon!
```

## Data Storage

Tasks are saved automatically to `data/mochizuki.txt` in the working directory. The file is created if it does not exist.
