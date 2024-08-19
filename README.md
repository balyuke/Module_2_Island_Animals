# Module_2_Island_Animals
Island of animals

## Задача

Твоя задача — запрограммировать модель острова с изменяемыми параметрами, состоящую из массива локаций (например, 100х20 клеток). Локации будут заполнены растительностью и животными. Животные могут:

- есть растения и/или других животных (если в их локации есть подходящая еда),
- передвигаться (в соседние локации),
- размножаться (при наличии пары в их локации),
- умирать от голода или быть съеденными.

## ООП
Создавая разнообразие животных, нужно по максимуму использовать ООП: все виды будут происходить от одного абстрактного класса Animal, который будет содержать поведение, общее для всех животных. Если у конкретных животных будут особенности питания, размножения, передвижения и т. д., то для них нужно будет переопределить методы класса Animal.
Что нужно сделать:
1. Создай иерархию классов:
Хищник: Волк, Удав, Лиса, Медведь, Орел
Травоядные: Лошадь, Олень, Кролик, Мышь, Коза, Овца, Кабан, Буйвол, Утка, Гусеница
Растения

![img](https://github.com/user-attachments/assets/14c3ca2a-9636-420d-a1b8-69adcd68f936)


Задана вероятность с которой животное съедает "пищу", если они находятся на одной клетке. Давай посмотрим на ситуацию "волк ест кролика". В таблице число 60. Это означает, что волк может съесть кролика, если они на одной клетке, с вероятностью 60%. Нужно использовать многопоточный random.

2. У животного должны быть методы: покушать, размножиться, выбрать направление передвижения.

3. В классах травоядного и хищника можно реализовать метод покушать. Но обрати внимание, есть травоядное утка, которое ест гусеницу.

4. В конкретных классах того или иного вида можно дорабатывать все методы под особенности животного.

5. Должно быть создано минимум 10 видов травоядных и 5 видов хищников (описаны в п. 1).

Конечно, можно написать всю программу в одном потоке, используя только циклы. Но нам нужно практически поработать с многопоточностью, поэтому обязательно нужно использовать потоки и пулы потоков. Один scheduled пул — для запуска по расписанию задания роста растений, задания жизненного цикла животных, и задания вывода статистики по системе. Как вариант, можно выводить статистику в одном задании с растениями или животными. Внутри задания жизненного цикла животных можно использовать еще один пул потоков — обычный. Какие задачи отдавать на выполнение этому пулу — решай самостоятельно.

## Обязательная часть задания:
- Иерархия животных (ООП)
- Поведение животных
- Многопоточность
- Статистика по состоянию острова на каждом такте (в консоль)

## Опциональная часть задания:

- Вынести параметры в одно место, чтоб было удобно управлять “симуляцией”
- Графика вместо консольной статистики. Это может быть как псевдо-графика в консоли, так и JavaFX, Swing…
- Добавь другие факторы, которые могут влиять на симуляцию:
  - больше видов животных
  - разные виды растений
  - кастомное поведение для группы животных (к примеру, волки охотятся и передвигаются не по одному, а стаей)
  - рельеф на земле, в том числе река, которая препятствует передвижению некоторых животных

## О параметрах (если решишь делать)
Чтобы при запуске программы было удобно менять различные ее параметры (размер острова, максимально допустимое количество растений/животных в одной клетке, вероятность передвижения того или иного вида животных, количество приплода у различных видов и т. д.), нужно все эти параметры вынести куда-то, например, в отдельный класс. Должна быть возможность изменять следующие параметры:

- размер острова
- длительность такта симуляции
- количество животных каждого вида на старте симуляции
- условие остановки симуляции (например, умерли все животные)
- количество детенышей у каждого вида животных

## Юникод (если решишь делать псевдографику)

Для изображения животных можно использовать юникод символы: 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐, 🐺, 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛.

## Справка

1) Сделаны все типы животных (иерархия животных ООП).\
Общим для всех живых организмов, кроме травы является абстрактный класс Animal. Его наследуют классы Carnivorous плотоядные и Herbivorous травоядные.
Названия животных перечислены в перечислении Species.
Заданные характеристики животных\существ\травы
- вес одного растения\животного                                           weight,
- максимальное количество растений\животных этого вида на одной клетке    maxAreaPopulation,
- скорость перемещения                                                    speed,
- сколько килограммов пищи нужно животному для полного насыщения          maxSatiety
описаны в аннотации PresetData, которая используется в соответствующих классах через механизм через Reflection API.
Параметры (размеры острова, задержки и т.п.) задаются в классе Params.

2) Действия животных (питание, размножение, перемещение и умирание) атомарны (берется лок на область острова\ячейку для каждого действия из четырех);

2.1. Питание: Хищники + Кабан,Утка,Мышь отличаются от прочих травоядных переопределенным методом getChanceToEat().
Так же у каждого хищника свой метод chanceOfEat(), определяющий через switch вкусовые предпочтения. Утка ест и растения и гусеницу (переопреден eat()).

2.2. Размножение: У животных есть пол, который задается через Random() в конструкторе. Каждая самка в ячейке произведет потомство, если в ячейке есть хотя бы один самец её вида.
Потомство добавляется в ту же ячейку.

2.3. Перемещение: Направление и скорость движения выбираются через Random().
Если выбранные параметры перемещения невозможны (слишком много животных в новой ячейке, остров закончился), движения не происходит (удаление\добавление животных из соотвествующих списков областей).

2.4. Умирание:\
2.4.1. Растения умирают, когда их вес меньше или равен нулю (их съели). Убыток веса растения равен голоду травоядного\
2.4.2. Животные умирают от голода (при движении тратится 10% сытости) или, если их съели.
Если сытость хищника меньше максимального значения, он убивает жертву любого размера (жертва гибнет вне зависимости от соотношений веса жертвы и сытости хищника).
Животные умирают от голода (при движении тратится 10% сытости) или, если их съели.

3) Многопоточность: Созданы потоки-классы\
3.1. Для развития локаций AreaThread: животные питаются eat(), размножаются reproduce(), двигаются move(), умирают due()\
3.2. Для выращивания травы PlantThread - метод grow()\
3.3. Для вывода Статистики InfoThread - метод infoStat()\

4) Вывод (статистика)\
4.1. Консольный вывод: каждая ячейка поля выводится в табличной форме с помощью разделителей, в ячейке указано
максимальное количество существ каждого подвида (растения, хищники, травоядные соответственно),
в начале идет общая статистика по растениям и животным, а в конце указывается такт\итерация симуляции.
<pre>
    ***********************************************
    Plants in total: 40000
    Carnivorous population: 1
    Herbivorous population: 0
    Animals in total: 1
    Iteration=38
    ***********************************************
</pre>

4.2. Более развернутая информация по популяции каждого вида животного в разрезе каждой локации пишется в лог-файл
<pre>
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main: Area [1, 4]
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:  - Carnivorous:
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - BEAR : 3
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - BOA : 36
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - EAGLE : 20
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - FOX : 1
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - WOLF : 28
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:  - Herbivorous:
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - BOAR : 50
    2024-08-17 12:21:01,502 TRACE com.javarush.baliuk.islandofanimals.Main:    - BUFFALO : 6
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - DEER : 18
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - DUCK : 131
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - GOAT : 87
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - HORSE : 19
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - MOUSE : 257
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - RABBIT : 150
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:    - SHEEP : 113
    2024-08-17 12:21:01,503 TRACE com.javarush.baliuk.islandofanimals.Main:  - Plants : 567
</pre>

## TODO List

- Сделать загрузку конфигурации (размеры острова, константы классов животных) из единого конфигурационного файла params.json с возможностью внесения изменений "на лету" без перекомпиляции классов;
- Сделать компактный (табличный) вывод через юникоды символов;
- Возможно пересмотреть модель выбора объектов для многопоточного выполнения. Разобраться почему не работают механизмы блокировки getLock().lock() и синхпрнизации synchronized(lock) при их использовании в Thread или Runnable.
