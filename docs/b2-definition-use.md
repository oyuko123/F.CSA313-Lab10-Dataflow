
# B2. Хувьсагчийн тодорхойлолт ба хэрэглээний жагсаалт (Definition-Use Listing)

## 1. Def/Use шинжилгээ

**Тодорхойлолт (Def):** Хувьсагчид шинэ утга оноож байгаа үйлдэл.

**Хэрэглээ (Use):** Хувьсагчийн утгыг уншиж ашиглаж байгаа үйлдэл.

| Мөр | Кодын үйлдэл | Definition (Def) | Use |
|---|---|---|---|
| 31 | Методын параметрүүд | attendance, threshold | — |
| 32 | Оролтын утгуудыг шалгах | — | attendance (P-use), threshold (P-use, attendance != null үед) |
| 33 | throw IllegalArgumentException | — | — |
| 35 | present = 0 | present | — |
| 36 | streak = 0 | streak | — |
| 37 | best = 0 | best | — |
| 38 | for (int a : attendance) | a | attendance (P-use: дараагийн элемент байгаа эсэх) |
| 39 | if (a > 0) | — | a (P-use) |
| 40 | present = present + 1 | present | present (C-use) |
| 41 | streak = streak + 1 | streak | streak (C-use) |
| 42 | if (streak > best) | — | streak (P-use), best (P-use) |
| 43 | best = streak | best | streak (C-use) |
| 46 | streak = 0 | streak | — |
| 49 | score = 0 | score | — |
| 50 | if (present >= threshold) | — | present (P-use), threshold (P-use) |
| 51 | score = 5 | score | — |
| 54 | score = score + 3 | score | score (C-use) |
| 56 | return score | — | score (C-use) |

## 2. Хэрэглээний төрлүүд

- **Def (Definition):** Хувьсагчид шинэ утга оноох үйлдэл.
- **C-use (Computational Use):** Хувьсагчийн утгыг тооцоолол, илэрхийлэл, оноолт эсвэл буцаах үйлдэлд ашиглах.
- **P-use (Predicate Use):** Хувьсагчийн утгыг нөхцөл шалгаж, програмын салааллыг тодорхойлоход ашиглах.

## 3. Чухал ажиглалтууд

1. 40-р мөр (`present = present + 1`) нь өмнөх утгыг уншиж, шинэ утга оноодог тул Def болон C-use хоёулаа болно.
2. 41-р мөр (`streak = streak + 1`) нь мөн өмнөх утгыг уншиж, шинэ утга оноодог тул Def болон C-use хоёулаа болно.
3. 43-р мөр (`best = streak`) нь `best` хувьсагчид шинэ утга оноож, `streak`-ийн утгыг ашиглана.
4. 46-р мөр (`streak = 0`) нь `streak`-ийн шинэ тодорхойлолт үүсгэж, өмнөх тодорхойлолтыг хүчингүй болгоно.
5. 38-р мөр нь enhanced for давталт бөгөөд `attendance` массивт дараагийн элемент байгаа эсэхийг шалгаж (P-use), тухайн элементийн утгыг `a` хувьсагчид онооно (Def).
6. 54-р мөр (`score = score + 3`) нь `score`-ийн өмнөх утгыг уншиж, шинэ утга оноодог тул Def болон C-use хоёулаа болно.
7. 32-р мөрөнд богино холболттой OR (`||`) оператор ашигласан тул `attendance == null` нөхцөл FALSE болсон тохиолдолд л `threshold < 0` нөхцөлийг шалгана.
