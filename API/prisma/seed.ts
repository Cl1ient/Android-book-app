import { PrismaClient } from '../src/generated/prisma/client';
import { PrismaLibSql } from "@prisma/adapter-libsql";
import { faker } from '@faker-js/faker';

const adapter = new PrismaLibSql({
    url: process.env.DATABASE_URL || ''
});
const prisma = new PrismaClient({ adapter });

// Fonction safe pour éviter undefined
function pickRandom<T>(arr: T[]): T {
    if (arr.length === 0) {
        throw new Error("Tableau vide");
    }

    const index = Math.floor(Math.random() * arr.length);
    const value = arr[index];

    if (value === undefined) {
        throw new Error("Index invalide");
    }

    return value;
}

async function main() {

    // 1. TAGS
    const tagNames = ['Fantasy', 'Horror', 'Sci-Fi', 'Romance', 'Thriller', 'History'];

    for (const name of tagNames) {
        await prisma.tag.upsert({
            where: { name },
            update: {},
            create: { name },
        });
    }

    console.log("Tags OK");

    // 2. AUTEURS + LIVRES
    console.log("Création des auteurs et livres...");

    for (let i = 0; i < 100; i++) {
        await prisma.author.create({
            data: {
                firstname: faker.person.firstName(),
                lastname: faker.person.lastName(),
                books: {
                    create: Array.from({
                        length: faker.number.int({ min: 1, max: 5 })
                    }).map(() => ({
                        title: faker.book.title(),
                        publication_year: faker.number.int({ min: 1800, max: 2024 }),
                        tags: {
                            connect: {
                                name: pickRandom(tagNames)
                            }
                        }
                    }))
                }
            }
        });
    }

    console.log("Auteurs + livres OK");

    // 3. USERS
    console.log("Création des users...");

    for (let i = 0; i < 50; i++) {
        await prisma.user.create({
            data: {
                email: faker.internet.email(),
                username: faker.internet.username(),
            }
        });
    }

    console.log("Users OK");

    // 4. COMMENTS + RATINGS
    console.log("Création des commentaires et notes...");

    const books = await prisma.book.findMany();
    const users = await prisma.user.findMany();

    for (const book of books) {

        const nbInteractions = faker.number.int({ min: 1, max: 5 });

        const usedUserIds = new Set<number>();

        while (usedUserIds.size < nbInteractions) {

            const user = pickRandom(users);

            // éviter doublon user pour un même livre
            if (usedUserIds.has(user.id)) continue;

            usedUserIds.add(user.id);

            // COMMENT
            await prisma.comment.create({
                data: {
                    content: faker.lorem.sentence(),
                    bookId: book.id,
                    userId: user.id,
                }
            });

            // RATING (unique bookId + userId)
            await prisma.rating.create({
                data: {
                    value: faker.number.int({ min: 1, max: 5 }),
                    bookId: book.id,
                    userId: user.id,
                }
            });
        }
    }

    console.log("Commentaires + notes OK");
    console.log("Seed terminé !");
}

main()
    .then(() => prisma.$disconnect())
    .catch(async (e) => {
        console.error(e);
        await prisma.$disconnect();
        process.exit(1);
    });