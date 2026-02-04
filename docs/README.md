# Mochizuki User Guide

Mochizuki is a calm, minimal task tracker for to-dos, deadlines, and events. It saves automatically, supports fast keyword search, and can show tasks that occur on a specific date.

<img src="images/mochizuki.png" alt="Mochizuki screenshot" width="700">

## Contents

- [Quick Start](#quick-start)
- [Command Summary](#command-summary)
- [Features](#features)
- [Date Formats](#date-formats)
- [FAQ](#faq)
- [Data Storage](#data-storage)

## Quick Start

1. Ensure Java 17 is installed.
2. Run:
   ```
   java -jar Mochizuki.jar
   ```
3. Enter a command and press Enter.

## Command Summary

| Command | Description |
|---|---|
| `list` | Show all tasks |
| `todo DESCRIPTION` | Add a to-do |
| `deadline DESCRIPTION /by YYYY-MM-DD` | Add a deadline |
| `event DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD` | Add an event |
| `mark INDEX` | Mark a task as done |
| `unmark INDEX` | Mark a task as not done |
| `delete INDEX` | Remove a task |
| `find KEYWORD` | Find tasks by keyword |
| `find-date YYYY-MM-DD` | Find tasks occurring on a date |
| `bye` | Exit |

## Features

### Add a to-do

**Command:** `todo borrow book`  
**Result:**
```
 Got it. I've added this task:
   [T][ ] borrow book
 Now you have 1 tasks in the list.
```

### Add a deadline

**Command:** `deadline return book /by 2019-12-02`  
**Result:**
```
 Got it. I've added this task:
   [D][ ] return book (by: Dec 2 2019)
 Now you have 2 tasks in the list.
```

### Add an event

**Command:** `event project meeting /from 2019-12-01 /to 2019-12-03`  
**Result:**
```
 Got it. I've added this task:
   [E][ ] project meeting (from: Dec 1 2019 to: Dec 3 2019)
 Now you have 3 tasks in the list.
```

### List tasks

**Command:** `list`  
**Result:**
```
 Here are the tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] return book (by: Dec 2 2019)
 3.[E][ ] project meeting (from: Dec 1 2019 to: Dec 3 2019)
```

### Mark a task as done

**Command:** `mark 2`  
**Result:**
```
 Nice! I've marked this task as done:
   [D][X] return book (by: Dec 2 2019)
```

### Unmark a task

**Command:** `unmark 2`  
**Result:**
```
 OK, I've marked this task as not done yet:
   [D][ ] return book (by: Dec 2 2019)
```

### Delete a task

**Command:** `delete 3`  
**Result:**
```
 Noted. I've removed this task:
   [E][ ] project meeting (from: Dec 1 2019 to: Dec 3 2019)
 Now you have 2 tasks in the list.
```

### Find tasks by keyword

**Command:** `find book`  
**Result:**
```
 Here are the matching tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] return book (by: Dec 2 2019)
```

### Find tasks by date

**Command:** `find-date 2019-12-02`  
**Result:**
```
 Tasks on Dec 2 2019:
 1.[D][ ] return book (by: Dec 2 2019)
 2.[E][ ] project meeting (from: Dec 1 2019 to: Dec 3 2019)
```

### Exit

**Command:** `bye`  
**Result:**
```
 Bye. Hope to see you again soon!
```

## Date Formats

- Input format: `YYYY-MM-DD` (e.g., `2019-12-02`)
- Output format: `MMM d yyyy` (e.g., `Dec 2 2019`)

## FAQ

**Why does `deadline` or `event` reject my date?**  
Dates must be in `YYYY-MM-DD`. Example: `2019-12-02`.

**What happens if the data file is missing?**  
Mochizuki will start with an empty list and create the file when you add a task.

**My data file looks corrupted. What happens?**  
Invalid lines are skipped, and the rest of the tasks still load.

## Data Storage

Tasks are saved automatically to `data/mochizuki.txt` in the working directory. The file is created if it does not exist.
