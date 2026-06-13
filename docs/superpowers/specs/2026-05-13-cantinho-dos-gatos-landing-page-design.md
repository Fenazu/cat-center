# Design: Landing Page — Cantinho dos Gatos

**Data:** 2026-05-13  
**Projeto:** aula (Spring Boot + Thymeleaf)  
**Fase:** 1 de 2 — HTML + CSS estático

---

## Objetivo

Criar uma landing page simples para o abrigo de gatos "Cantinho dos Gatos", em português (PT-BR), com cores pastéis (principalmente rosa). O objetivo principal é aprender o fluxo Spring Boot → MVC → Thymeleaf → HTML + CSS.

---

## Escopo da Fase 1

Apenas HTML + CSS usando Thymeleaf. Nenhum dado Java ainda — o conteúdo será hardcoded diretamente no template.

**Fora do escopo desta fase:**
- Classe `Gato.java`
- Dados passados pelo Controller
- `th:each` ou qualquer diretiva Thymeleaf dinâmica

---

## Seções da Página

### 1. Hero
- Nome do abrigo: **Cantinho dos Gatos** com emoji de patinha (🐾)
- Frase de apoio: *"Um lar para quem precisa de amor"*
- Botão de chamada: **"Adote um gatinho ❤️"** (sem ação por enquanto)
- Fundo: gradiente rosa pastel (`#fce4ec` → `#f8bbd0`)

### 2. Gatinhos para Adoção
- Título da seção: **"🐱 Gatinhos para Adoção"**
- 3 cards hardcoded, cada um com: emoji do gato, nome, idade/gênero, descrição e botão "Quero adotar"
- Gatinhos de exemplo:
  - **Bolinha** — 2 anos, Fêmea, Carinhosa e brincalhona
  - **Mingau** — 1 ano, Macho, Tranquilo e independente
  - **Fofinha** — 3 anos, Fêmea, Tímida mas muito amorosa
- Cards com fundo branco, borda rosa suave, bordas arredondadas

---

## Paleta de Cores

| Uso                  | Cor            | Hex       |
|----------------------|----------------|-----------|
| Fundo hero           | Rosa claro     | `#fce4ec` |
| Gradiente hero       | Rosa médio     | `#f8bbd0` |
| Fundo página         | Rosa bem claro | `#fff5f8` |
| Títulos / destaque   | Rosa escuro    | `#c2185b` |
| Texto secundário     | Rosa médio     | `#ad1457` |
| Botão principal      | Rosa vibrante  | `#e91e63` |
| Borda dos cards      | Rosa suave     | `#f8bbd0` |

---

## Arquitetura (Fase 1)

```
Navegador → GET /
              ↓
        MenuController.java
        (já existe, sem alterações)
              ↓
        index.html (Thymeleaf)
              ↓
        index.css (estilos)
```

### Arquivos alterados

| Arquivo | Ação |
|---|---|
| `src/main/resources/templates/index.html` | Substituir o "Hello World" pelo layout completo |
| `src/main/resources/static/css/index.css` | Substituir com os estilos pastéis |
| `src/main/java/com/exemplo/aula/MenuController.java` | Nenhuma alteração |

---

## Estrutura HTML (resumo)

```
<body>
  <!-- Hero -->
  <header class="hero">
    <h1>🐾 Cantinho dos Gatos</h1>
    <p>Um lar para quem precisa de amor</p>
    <a href="#" class="btn-adotar">Adote um gatinho ❤️</a>
  </header>

  <!-- Gatinhos -->
  <section class="gatos">
    <h2>🐱 Gatinhos para Adoção</h2>
    <div class="cards">
      <div class="card">...</div>  <!-- Bolinha -->
      <div class="card">...</div>  <!-- Mingau -->
      <div class="card">...</div>  <!-- Fofinha -->
    </div>
  </section>
</body>
```

---

## CSS (abordagem)

CSS simples, sem frameworks. Apenas:
- Variáveis de cor no `:root`
- `flexbox` para alinhar os cards lado a lado
- Classes semânticas: `.hero`, `.gatos`, `.card`, `.btn-adotar`

---

## Fase 2 (fora do escopo agora)

Quando a fase 1 estiver funcionando, a fase 2 consistirá em:

1. Criar `Gato.java` com atributos: `nome`, `idade`, `genero`, `descricao`
2. Atualizar `MenuController.java` para criar uma lista de `Gato` e passá-la ao `Model`
3. Usar `th:each="gato : ${gatos}"` no template para renderizar os cards dinamicamente
