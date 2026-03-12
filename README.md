# Java Chess

Українська | [English](#english)

## Опис

Java Chess - це шахова гра з графічним інтерфейсом, написана на Java з використанням Swing. Гра підтримує всі стандартні правила шахів, включаючи шах і мат.

## Функціонал

- **Ходи фігур**: Підтримуються всі стандартні ходи для кожної фігури
- **Взяття фігур**: Можна захоплювати фігури противника
- **Шах**: Автоматичне визначення ситуації шаху
- **Мат**: Автоматичне визначення мата та завершення гри
- **Історія ходів**: Збереження всіх зроблених ходів
- **Черга ходів**: Чергування ходів між білими та чорними

## Запуск гри

### Вимоги

- Java JDK 17 або вище
- IntelliJ IDEA (рекомендовано) або інший IDE

### Інструкція з запуску

1. Відкрийте проект в IntelliJ IDEA
2. Перейдіть до `ChessBoard.java` (src/main/java/ChessBoard.java)
3. Натисніть правою кнопкою миші → `Run 'ChessBoard.main()'`

### Альтернативний запуск через командний рядок

```bash
# Компіляція
javac -d out src/main/java/*.java src/main/java/**/*.java

# Запуск
java -cp out ChessBoard
```

## Як грати

1. **Вибір фігури**: Клікніть на фігуру свого кольору (починають білі)
2. **Хід**: Клікніть на клітинку, куди хочете походити
3. **Недійсний хід**: Якщо хід недійсний, з'явиться повідомлення "Invalid move"
4. **Шах**: При шаху з'явиться повідомлення "CHECK!"
5. **Мат**: При маті гра завершується з повідомленням "CHECKMATE! X wins!"

## Структура проекту

```
chess/
├── src/main/java/
│   ├── ChessBoard.java          # Головний клас з GUI
│   ├── logic/
│   │   ├── GameLogic.java       # Логіка шахів (шах, мат)
│   │   └── Move.java            # Клас для запису ходів
│   └── pieces/
│       ├── Piece.java           # Абстрактний клас фігури
│       ├── King.java            # Король
│       ├── Queen.java           # Ферзь
│       ├── Rook.java            # Тура
│       ├── Elephant.java        # Слон (офіцер)
│       ├── Knight.java          # Кінь
│       └── Pawn.java            # Пішак
```

## Технічні деталі

### Основні методи та їхня логіка

#### 1. **isValidMove()** - Перевірка валідності ходу
Кожна фігура має власну реалізацію цього методу:

**Тура (Rook)**:
- Рухається тільки по горизонталі або вертикалі
- Перевіряє, чи немає фігур на шляху
- Може взяти фігуру противника, але не може перестрибувати через свої

```java
// Тура перевіряє, чи хід по прямій лінії
if (targetY != this.y && targetX != this.x) {
    return false; // не по прямій - хід недійсний
}
```

**Пішак (Pawn)**:
- Рухається вперед на 1 клітинку
- З початкової позиції - на 2 клітинки
- Б'є по діагоналі на 1 клітинку
- Не може рухатися назад

```java
// Напрямок руху залежить від кольору
int direction = isWhite ? -1 : 1; // білі йдуть вгору, чорні - вниз
```

#### 2. **isKingInCheck()** - Перевірка шаху
Логіка роботи:
1. **Знаходить короля** заданого кольору на дошці
2. **Перевіряє всі фігури** противника
3. **Для кожної фігури** викликає `isValidMove()` до позиції короля
4. Якщо хоча б одна фігура може атакувати короля - шах!

```java
// Пошук короля
for (int y = 0; y < board.length; y++) {
    for (int x = 0; x < board.length; x++) {
        if (board[y][x] instanceof King && board[y][x].isWhite() == isWhiteKing) {
            kingX = x; kingY = y; // знайшли короля
        }
    }
}
```

#### 3. **isCheckMate()** - Перевірка мату
Складний алгоритм:
1. Спочатку перевіряє, чи король взагалі під шахом
2. Якщо так - перебирає ВСІ фігури гравця
3. Для кожної фігури перевіряє ВСІ можливі ходи
4. Симулює кожен хід на копії дошки
5. Перевіряє, чи після симуляції король все ще під шахом
6. Якщо НІОДНОГО ходу не рятує від шаху - мат!

#### 4. **copyBoard()** - Глибоке копіювання дошки
Створює повну копію ігрової дошки для симуляції ходів:
- Створює новий двовимірний масив
- Копіює кожну фігуру через метод `copy()`
- Дозволяє тестувати ходи без зміни основної дошки

#### 5. **wouldMoveBeLegal()** - Перевірка легальності ходу
Симулює хід і перевіряє, чи він не залишає короля під шахом:
1. Копіює дошку
2. Робить симульований хід
3. Перевіряє, чи король під шахом після ходу
4. Відміняє симуляцію
5. Повертає результат

### Як працює гра крок за кроком

#### Клік по фігурі:
```java
if (selectedPiece == null) {
    // Перший клік - вибір фігури
    if (board[row][col] != null && board[row][col].isWhite() == currentTurnIsWhite) {
        selectedPiece = board[row][col]; // вибрали фігуру
    }
}
```

#### Клік по клітинці призначення:
```java
if (selectedPiece.isValidMove(targetRow, targetCol, board)) {
    // Хід валідний - виконуємо
    moveHistory.add(new Move(...)); // зберігаємо в історію
    // переміщуємо фігуру
    // перевіряємо шах/мат суперника
    currentTurnIsWhite = !currentTurnIsWhite; // міняємо хід
}
```

### Важливі концепції для розуміння

1. **Двовимірна дошка**: `board[y][x]` - Y це рядок, X це колонка
2. **Поліморфізм**: Всі фігури успадковуються від `Piece` але мають різну логіку `isValidMove()`
3. **Симуляція**: Для перевірки мату гра симулює всі можливі ходи
4. **Стан гри**: Зберігається в дошці, історії ходів та черзі

### Поради для новачків:
- Розумійте систему координат: `[0][0]` - верхній лівий кут
- Кожен хід перевіряється двічі: валідність фігури + легальність щодо шаху
- Історія ходів потрібна для можливих розширень (відкат ходів, аналіз партії)

---

<a name="english"></a>
# English

## Description

Java Chess is a chess game with a graphical interface written in Java using Swing. The game supports all standard chess rules, including check and checkmate.

## Features

- **Piece Movement**: Supports all standard moves for each piece
- **Capturing**: Can capture opponent's pieces
- **Check**: Automatic detection of check situations
- **Checkmate**: Automatic detection of checkmate and game end
- **Move History**: Saves all made moves
- **Turn Queue**: Alternates turns between white and black

## Running the Game

### Requirements

- Java JDK 17 or higher
- IntelliJ IDEA (recommended) or other IDE

### Running Instructions

1. Open the project in IntelliJ IDEA
2. Navigate to `ChessBoard.java` (src/main/java/ChessBoard.java)
3. Right-click → `Run 'ChessBoard.main()'`

### Alternative Command Line Launch

```bash
# Compilation
javac -d out src/main/java/*.java src/main/java/**/*.java

# Run
java -cp out ChessBoard
```

## How to Play

1. **Select Piece**: Click on your color piece (white starts)
2. **Move**: Click on the square where you want to move
3. **Invalid Move**: If move is invalid, "Invalid move" message appears
4. **Check**: When in check, "CHECK!" message appears
5. **Checkmate**: When checkmate, game ends with "CHECKMATE! X wins!"

## Project Structure

```
chess/
├── src/main/java/
│   ├── ChessBoard.java          # Main GUI class
│   ├── logic/
│   │   ├── GameLogic.java       # Chess logic (check, checkmate)
│   │   └── Move.java            # Move recording class
│   └── pieces/
│       ├── Piece.java           # Abstract piece class
│       ├── King.java            # King
│       ├── Queen.java           # Queen
│       ├── Rook.java            # Rook
│       ├── Elephant.java        # Bishop
│       ├── Knight.java          # Knight
│       └── Pawn.java            # Pawn
```

## Technical Details

- **isValidMove()**: Each piece has its own move validation logic
- **isKingInCheck()**: Checks if king is under attack
- **isCheckMate()**: Checks all possible moves to escape check
- **copyBoard()**: Deep board copy for move simulation
