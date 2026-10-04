This is a replica of the original ,,Tower Bloxx" game. 

**Features**

- **Free play mode** with an endless, growing tower
- **Real physics** (Box2D via FXGL): floors have mass, friction and gravity, and collapse realistically
- **Smooth camera** that follows the tower upward using linear interpolation
- **Persistent high scores** saved to disk, with the current record holder shown at game over
- **Multiple controls**: `Space`, `S`, `↓` or a mouse click drops the floor
- **Randomized floors**: each floor spawns at a random position, speed and direction
- **In-game tutorial** dialog on startup

**How to Play:**

1. Watch the floor swing left and right.
2. Press **Space / S / ↓** or **click** to drop it.
3. Stack floors as high as you can. If a floor falls off the tower, the game ends.
4. Enter your name to save your score and see who holds the record.

**Running the Project**

**Requirements:** JDK [version], Gradle (wrapper included)

```bash
git clone [your-repo-url]
cd Torniehitaja
./gradlew run
```

**Built With**

Java · JavaFX · FXGL · Box2D · Gradle


KNOWN BUGS:
  - At the start of the game it is possible to put multiple floors side by side without the game failing
  - Due to a physics bug the bounciness of the tower becomes so strong that it bounces off screen
  - Tower floors do not lock onto each other, which results in a very small drift on the lower floors causing the whole tower to collapse at some point
