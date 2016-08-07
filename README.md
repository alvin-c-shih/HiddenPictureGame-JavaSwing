# HiddenPictureGame-JavaSwing

Hidden Picture Game for teaching addition and subtraction implemented
using Java Swing.

# Warning

This is a very, very rough piece of work.  It was only developed to
the point of being *barely* useful for my very specific purpose.

No warranties stated or implied!

With that out of the way...

# Introduction

Back when my first daughter was around 3 years old, I was trying to
get her to memorize her addition / subtraction facts.  She also was a
bit behind on her fine motor skills and found writing answers on paper
sheets a bit frustrating.

I found this online Hidden Picture Game and thought it would be
perfect for her!

* http://www.aplusmath.com/games/HiddenPicture/HiddenPicture.php

Alas, it was met with resistance for the following reasons:

1. She said the pictures were too boyish.

2. It assumed mastery of 0+0 through 9+9, whereas I wanted to be able
    to select which questions to emphasize and provide mnemonics.

I hacked together this application in Java Swing to allow me to load
in "girlish" images (Disney Princesses, Hello Kitty, etc.) that she'd
be motivated to reveal.  The questions are controlled from the beans.xml file.

# Addition / Subtraction Mnemonics

This program does not teach math by itself.  It assumes the child has
been exposed to addition / subtraction facts from a book.  The book I
used was: "Two Plus Two Is Not Five".

* http://www.longevitypublishing.com/

The application just provides extra practice to lower the error rate
and improve speed.

The book provides helpful diagrams and explanations.  I liked to
verify basic understanding by doing exercises together from the book
before moving on to the app.

I sometimes had to supplement with "counters" (like blocks) to help
assure the kid that the math fact holds true.

# Why Java?

The iPad had just come out around that time and I didn't have a Mac
for development.  I'd purchased a used Tablet PC which made it easy
enough for my kid to play the game.

It turned out ok in the end, since it made the Android port easier, I
think.

Although the Table PC is now a thing of the past, there are now
Windows 10 tablets.  I got a 10" Win10 tablet from Walmart for $90!

# How to get started.

1. Install "Eclipse IDE for Java Developers"
2. HiddenPictureGame-JavaSwing to the directory of your choice.
3. File -> Import -> General -> Existing Projects Into Workspace
    -> Select Root Directory -> [.../HiddenPictureGame-JavaSwing]
4. Window -> Show View -> Package Explorer
5. Right click HiddenPictureGame-JavaSwing.
6. Run As -> Java Application

# How to start hacking it.

## Pictures

The program picks an image at random from the "resources" folder.  Add
or remove images from there based on what your child finds motivating.

## Questions

The kinds of questions that come out are controlled by the beans
instantiated in beans.xml .

The `Main.main(...)` method picks out a "game" implementing the
`game_logic.GameI` interface.  It's hard-wired to pick out the bean
named `gameCycler`.

Backing up a bit, a few levels of abstraction have built up around
`GameI`:

1. `FlashCardGame` : Tests a related list of math facts.

2. `ComposerGame` : Picks randomly from a list of `FlashCardGames`.

3. `GameCycler` : Cycles through a list of subgames, be they `ComposerGames` or `FlashCardGames`.

I start with `GameCycler` since I may have limied time, or my kid
might have limited mental stamina that day.  `GameCycler` allows me to
put specific games ahead of others.

`ComposerGame` allows me to put together questions randomly drawn from
a set of FlashCardGames.  If learning using mnemonics, such as those
from "Two Plus Two is Not Five", part of the trick is recognizing
which mnemonic to use.

## More about `FlashCardGame`

Here's an example instance from `beans.xml`

```
<bean id="doublesSubtraction" class="FlashCardGame" init-method="init">
  <property name="name" value="Doubles Subtraction"/>
  <property name="exprSpec" value="10-5;8-4;6-3;4-2"/>
</bean>
```

You can see that the `exprSpec` property allows one to configure a
list of semicolon-separated math facts that go under the same
mnemonic.

The parsing happens in `FlashCardGame.generate(...)` and currently
only supports addition and subtraction.

## AdditionGame / SubtractionGame

These are useful once the student has mastered enough mnemonics that
you can throw whole ranges of problems at them without having to list
out a dozen (or more) FlashCardGames.

# Bugs.

Too many to count.  :-)

Happy to receive pull requests!

