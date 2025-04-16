# TDD를 적용하면서 구현해본 안드로이드 Major component

## Index
* [Calendar](#Calendar)

<br><br><br>

## Calendar

Name | Demo
--- | ---
**NormalCalendar** | ![NormalCalendar](https://github.com/user-attachments/assets/4ae59921-a80b-40e9-9629-a9b3edc94cb1)

### Example
```kotlin
val calendarState = rememberCalendarState()

NormalCalendar(
    calendarState = calendarState,
    onDateSelect = { Log.d("NormalCalendar", "selectedDate : $it") },
)
```

<br><br><br>

Name | Demo
--- | ---
**SwipeableCalendar** | ![SwipeableCalendar](https://github.com/user-attachments/assets/aba53351-1f6a-4659-bea2-3380d5172dc2)

### Example
```kotlin
val calendarState = rememberCalendarState()

SwipeableCalendar(
    calendarState = calendarState,
    onDateSelect = { Log.d("NormalCalendar", "selectedDate : $it") },
)
```
