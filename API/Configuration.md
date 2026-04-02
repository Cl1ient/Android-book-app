# INSTALL
bun install

# RUN
bun run index.ts

# =========================
# PRISMA RESET + SEED
# =========================

# Générer le client
bunx prisma generate

# RESET COMPLET (avec migrations)
bunx prisma migrate reset

# (optionnel) seed manuel
bunx prisma db seed