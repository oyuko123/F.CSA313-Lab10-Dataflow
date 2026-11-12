# Лаборатори №10 — Өгөгдлийн урсгалын тест

**Хичээл:** F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест (2026)

## Оюутны мэдээлэл

- **Нэр:** Т.Оюунжаргал
- **Оюутны код:** B232270149

## Орчны мэдээлэл

### Java version

```text
java version "21.0.2" 2024-01-16 LTS
Java(TM) SE Runtime Environment (build 21.0.2+13-LTS-58)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.2+13-LTS-58, mixed mode, sharing)
```

### Maven version

```text
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: C:\Users\myagaa\Downloads\apache-maven-3.9.9-bin\apache-maven-3.9.9
Java version: 21.0.2, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-21
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

---

# Хэсэг A — GCD Definition-Use анализ

## A1. Definition ба Use цэгүүд

GCD функцэд ашиглагдаж буй хувьсагч бүрийн Definition (def) болон Use (c-use, p-use) цэгүүдийг тодорхойлов.

| Хувьсагч | Definition (мөр) | Use (мөр) |
|---|---|---|
| x | 13, 17 | 16 (C-use), 20 (C-use) |
| y | 13, 18 | 15 (P-use), 16 (C-use), 17 (C-use) |
| tmp | 16 | 18 (C-use) |

**Тайлбар:**
- **Definition (def):** Хувьсагчид утга оноож байгаа цэг.
- **C-use:** Хувьсагчийн утгыг тооцоолол эсвэл илэрхийлэлд ашиглах.
- **P-use:** Хувьсагчийн утгыг нөхцөл шалгахад ашиглах.

## A2. GCD-ийн бүрэн DU хосууд

DU хос гэдэг нь тодорхойлолтоос (def) хэрэглээний цэг (use) хүртэл тухайн хувьсагч дахин тодорхойлогдоогүй (def-clear) замаар холбогдсон хос юм.

| ID | Хувьсагч | Def мөр | Use мөр | Төрөл | Def-clear зам | Тест |
|---|---|---:|---:|---|---|---|
| G1 | x | 13 | 16 | C-use | 13 → 15(T) → 16 | T2 |
| G2 | x | 13 | 20 | C-use | 13 → 15(F) → 20 | T1 |
| G3 | x | 17 | 16 | C-use | 17 → 18 → 15(T) → 16 | T2 |
| G4 | x | 17 | 20 | C-use | 17 → 18 → 15(F) → 20 | T2 |
| G5 | y | 13 | 15 | P-use (T) | 13 → 15(T) | T2 |
| G6 | y | 13 | 15 | P-use (F) | 13 → 15(F) | T1 |
| G7 | y | 13 | 16 | C-use | 13 → 15(T) → 16 | T2 |
| G8 | y | 13 | 17 | C-use | 13 → 15(T) → 16 → 17 | T2 |
| G9 | y | 18 | 15 | P-use (T) | 18 → 15(T) | T2 |
| G10 | y | 18 | 15 | P-use (F) | 18 → 15(F) | T2 |
| G11 | y | 18 | 16 | C-use | 18 → 15(T) → 16 | T2 |
| G12 | y | 18 | 17 | C-use | 18 → 15(T) → 16 → 17 | T2 |
| G13 | tmp | 16 | 18 | C-use | 16 → 17 → 18 | T2 |

**Нийт: 13 DU хос.**

T = True, F = False.

## A3. JUnit тестүүд

Тестүүдийг `src/test/java/mn/edu/must/sqat/GcdTest.java` файлд хэрэгжүүлсэн.

### T1 — gcd(7, 0)

```java
@Test
@DisplayName("T1: G2, G6")
void gcd_7_0_zeroIterations() {
    assertEquals(7, DataFlowTargets.gcd(7, 0));
}
```

- Хүлээгдэж буй үр дүн: 7.
- while давталт 0 удаа ажиллана.
- x-ийн 13-р мөрийн definition нь 20-р мөрийн return-д хүрнэ.
- y-ийн 13-р мөрийн definition нь 15-р мөрийн False салаанд хүрнэ.
- G2 болон G6 DU хосыг хамарна.

### T2 — gcd(12, 8)

```java
@Test
@DisplayName("T2: G1, G3-G5, G7-G13")
void gcd_12_8_twoIterations() {
    assertEquals(4, DataFlowTargets.gcd(12, 8));
}
```

- Хүлээгдэж буй үр дүн: 4.
- while давталт 2 удаа ажиллана.
- Давталтын явцад x болон y хувьсагч дахин тодорхойлогдоно.
- tmp хувьсагчийн 16-р мөрийн definition нь 18-р мөрийн use-д хүрнэ.
- G1, G3, G4, G5, G7, G8, G9, G10, G11, G12, G13 DU хосыг хамарна.

### A4. DU хамралтын дүгнэлт

Хоёр тестийг хамтад нь ажиллуулснаар GCD функцийн нийт 13 DU хос бүгд хамрагдсан.

`gcd(7, 0)` тест нь давталт огт ажиллахгүй тохиолдлыг шалгаж, эхний definition-ээс давталтын дараах return болон while нөхцөлийн False салаанд хүрэх хосуудыг хамардаг.

`gcd(12, 8)` тест нь давталт хоёр удаа ажиллах нөхцөлийг шалгаж, хувьсагчдын дахин тодорхойлолт, дараагийн давталтын use болон давталт дуусах үеийн нөхцөлийг хамардаг.

Иймээс эдгээр хоёр тест нь GCD функцийн All-DU-Pairs хамралтыг хангаж байна.

### A5. Тестийн гүйцэтгэл

`mvn clean verify` командаар JUnit тестүүдийг ажиллуулж, JaCoCo тайлан үүсгэв.

Одоогийн гүйцэтгэлийн үр дүн:

| Үзүүлэлт | Үр дүн |
|---|---:|
| Tests run | 2 |
| Failures | 0 |
| Errors | 0 |
| Skipped | 0 |
| Build | SUCCESS |

JaCoCo тайлангийн файл:

- `target/site/jacoco/index.html`
- `target/site/jacoco/jacoco.csv`

**Тайлбар:** JaCoCo нь кодын instruction, line болон branch хамралтыг хэмждэг. Харин DU-pairs хамралтыг гараар боловсруулсан DU хүснэгт болон JUnit тестүүдээр баталгаажуулсан.

---

*Тэмдэглэл: Энэ README-ийн дараагийн хэсгүүдэд Хэсэг B (bonus функцийн CFG, DU анализ, All-Defs болон All-DU-Pairs тест), Хэсэг C (Fault Seeding), эцсийн JaCoCo тайлан болон лабораторийн дүгнэлтийг нэмнэ.*
