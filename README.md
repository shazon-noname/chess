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

- **isValidMove()**: Кожна фігура має власну логіку перевірки ходів
- **isKingInCheck()**: Перевіряє, чи король під шахом
- **isCheckMate()**: Перевіряє всі можливі ходи для виходу з шаху
- **copyBoard()**: Глибоке копіювання дошки для симуляції ходів

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
