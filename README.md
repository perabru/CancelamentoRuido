# 🎧 Cancelamento de Ruído

Aplicativo Android desenvolvido em **Kotlin** para captura e análise do nível de ruído ambiente utilizando o microfone do smartphone.

O projeto realiza a leitura do sinal de áudio em tempo real, calcula sua intensidade e apresenta na tela uma estimativa do nível de ruído em decibéis (dB).

Além disso, possui uma função experimental de **simulação de cancelamento de ruído**, permitindo visualizar uma estimativa da quantidade de ruído que poderia ser reduzida.

---

## 📱 Sobre o projeto

O objetivo deste projeto é explorar conceitos relacionados a:

- Processamento digital de sinais;
- Captura de áudio;
- Intensidade sonora;
- RMS (*Root Mean Square*);
- Decibéis;
- Desenvolvimento Android;
- Kotlin;
- Processamento de áudio em tempo real;
- Cancelamento ativo de ruído.

O aplicativo utiliza o próprio **microfone do celular** como sensor para analisar o som presente no ambiente.

---

## 🚀 Funcionalidades

O aplicativo possui as seguintes funcionalidades:

- 🎤 Captura de áudio utilizando o microfone do smartphone;
- 📊 Medição contínua da intensidade do sinal;
- 🔊 Exibição do nível de ruído;
- 📐 Cálculo utilizando RMS;
- 🎚️ Conversão da intensidade para dB;
- 🔘 Botão para ativar e desativar a simulação de cancelamento;
- 📉 Exibição da quantidade estimada de ruído cancelado;
- 🔄 Atualização dos valores em tempo real.

---

## 🧠 Como funciona

O funcionamento básico do aplicativo pode ser representado pelo seguinte fluxo:

```text
        SOM DO AMBIENTE
               │
               ▼
      ┌───────────────────┐
      │ Microfone celular │
      └─────────┬─────────┘
                │
                ▼
      ┌───────────────────┐
      │    AudioRecord    │
      └─────────┬─────────┘
                │
                ▼
      ┌───────────────────┐
      │ Buffer de áudio   │
      │    PCM 16 bits    │
      └─────────┬─────────┘
                │
                ▼
      ┌───────────────────┐
      │   Cálculo RMS     │
      └─────────┬─────────┘
                │
                ▼
      ┌───────────────────┐
      │ Conversão para dB │
      └─────────┬─────────┘
                │
                ▼
       NÍVEL DE RUÍDO
          NA TELA
```

---

## 🎤 Captura do áudio

O projeto utiliza a classe:

```kotlin
AudioRecord
```

O áudio é capturado utilizando:

```text
Fonte de áudio: Microfone
Taxa de amostragem: 44.100 Hz
Canal: Mono
Formato: PCM 16 bits
```

Essas amostras são armazenadas temporariamente em um buffer para posteriormente serem analisadas.

---

## 📐 Cálculo do RMS

Para determinar a intensidade média do sinal é utilizado o cálculo do **RMS — Root Mean Square**.

Simplificadamente:

```text
RMS = √(soma das amostras² / quantidade de amostras)
```

No aplicativo:

```kotlin
var soma = 0.0

for (valor in buffer) {
    soma += valor * valor
}

val rms = sqrt(soma / buffer.size)
```

O RMS representa a intensidade média do sinal de áudio capturado.

---

## 🔊 Conversão para decibéis

Após calcular o RMS, o aplicativo converte o valor para uma escala logarítmica:

```text
dB = 20 × log10(RMS)
```

No código:

```kotlin
if (rms > 0) {
    db = 20 * log10(rms)
}
```

O resultado é apresentado na interface do aplicativo.

Exemplo:

```text
Ruído: 72.4 dB
```

---

## 🔇 Simulação de cancelamento

O aplicativo possui um botão:

```text
ATIVAR CANCELAMENTO
```

Quando ativado, o programa calcula uma estimativa correspondente a **30% do nível medido**.

Exemplo:

```text
Ruído: 70.0 dB
Cancelado: 21.0 dB
```

A lógica atual utiliza:

```kotlin
if (cancelamento) {
    cancelado = db * 0.30
}
```

O botão pode ser utilizado para alternar entre:

```text
ATIVAR CANCELAMENTO
```

e

```text
DESATIVAR CANCELAMENTO
```

---

## ⚠️ Importante

A versão atual do projeto apresenta uma **simulação matemática de redução de ruído**.

Ela ainda **não executa cancelamento ativo de ruído real**.

Um sistema ANC (*Active Noise Cancellation*) real precisaria:

1. Capturar o som externo;
2. Processar o sinal com baixa latência;
3. Determinar frequência, amplitude e fase;
4. Gerar uma nova onda com fase aproximadamente invertida;
5. Reproduzir essa onda por um alto-falante ou fone;
6. Realizar todo o processamento com latência extremamente baixa.

O princípio físico seria:

```text
Ruído original
      +
Sinal em fase invertida
      =
Redução do ruído
```

Portanto, este projeto representa uma base para estudo e evolução de técnicas de processamento de áudio.

---

# 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Kotlin | Linguagem principal |
| Android Studio | Desenvolvimento do aplicativo |
| Android SDK | Plataforma Android |
| AudioRecord | Captura do microfone |
| PCM 16-bit | Representação do áudio |
| RMS | Cálculo da intensidade |
| Log10 | Conversão para escala logarítmica |
| XML | Interface gráfica |
| Gradle | Build e gerenciamento do projeto |

---

# 📱 Requisitos

Para executar o projeto é recomendado possuir:

- Android Studio;
- Android SDK instalado;
- JDK compatível com Java 11;
- Smartphone Android ou emulador;
- Permissão para acesso ao microfone.

O projeto está configurado com:

```text
Minimum SDK: 24
Target SDK: 37
```

---

# 🔐 Permissão do microfone

Como o aplicativo precisa analisar o áudio ambiente, a seguinte permissão é utilizada:

```xml
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
```

Durante a execução, o Android também solicita autorização do usuário para acessar o microfone.

---

# ▶️ Como executar

## 1. Baixe ou clone o projeto

Clone este repositório utilizando Git ou utilize a opção **Download ZIP** do GitHub.

---

## 2. Abra no Android Studio

No Android Studio selecione:

```text
File
 ↓
Open
 ↓
Selecione a pasta CancelamentoRuido
```

---

## 3. Aguarde a sincronização

Espere o Android Studio concluir:

```text
Gradle Sync
```

---

## 4. Conecte um smartphone

Ative no dispositivo:

```text
Configurações
    ↓
Opções do desenvolvedor
    ↓
Depuração USB
```

Também é possível utilizar um emulador Android.

Para testes envolvendo microfone, um **smartphone físico é recomendado**.

---

## 5. Execute

Clique em:

```text
▶ Run
```

Selecione o dispositivo e aguarde a instalação.

---

## 6. Permita o acesso ao microfone

Na primeira execução, o Android solicitará:

```text
Permitir que o aplicativo use o microfone?
```

Selecione:

```text
PERMITIR
```

---

# 🖥️ Interface

A interface apresenta inicialmente:

```text
╔════════════════════════════╗
║                            ║
║        Ruído: 0 dB         ║
║                            ║
║      Cancelado: 0 dB       ║
║                            ║
║   ┌────────────────────┐   ║
║   │ ATIVAR CANCELAMENTO│   ║
║   └────────────────────┘   ║
║                            ║
╚════════════════════════════╝
```

Durante a execução os valores são atualizados continuamente.

---

# 📂 Estrutura principal

```text
CancelamentoRuido/
│
├── app/
│   │
│   ├── src/
│   │   └── main/
│   │       │
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── perabru/
│   │       │           └── cancelamentoruido/
│   │       │               └── MainActivity.kt
│   │       │
│   │       ├── res/
│   │       │   └── layout/
│   │       │       └── activity_main.xml
│   │       │
│   │       └── AndroidManifest.xml
│   │
│   └── build.gradle.kts
│
├── gradle/
│
├── build.gradle.kts
│
├── settings.gradle.kts
│
└── README.md
```

---

# 💻 Arquivos principais

### `MainActivity.kt`

Responsável por:

- Solicitar acesso ao microfone;
- Capturar o áudio;
- Criar o buffer;
- Calcular o RMS;
- Calcular o nível do sinal em dB;
- Atualizar a interface;
- Controlar a função de simulação do cancelamento.

---

### `activity_main.xml`

Responsável pela interface gráfica contendo:

- Valor do ruído;
- Valor cancelado;
- Botão para ativação e desativação.

---

### `AndroidManifest.xml`

Define as configurações principais do aplicativo e a permissão:

```xml
android.permission.RECORD_AUDIO
```

---

# 🔬 Conceito de Cancelamento Ativo de Ruído

O cancelamento ativo de ruído utiliza o princípio da **interferência destrutiva das ondas**.

Imagine uma onda:

```text
Sinal original

      /\        /\
     /  \      /  \
____/    \____/    \____
```

Um sinal invertido teria aproximadamente:

```text
Sinal invertido

____      ____      ____
    \    /    \    /
     \__/      \__/
```

Ao combinar os dois sinais:

```text
Sinal original
       +
Sinal invertido
       ↓
────────────────────────
      redução sonora
```

Esse princípio é utilizado em fones de ouvido com tecnologia **ANC — Active Noise Cancellation**.

---

# 🚧 Possíveis melhorias

Algumas evoluções possíveis para o projeto:

- [ ] Calibrar a medição para aproximar valores de dB SPL reais;
- [ ] Criar gráfico de intensidade sonora em tempo real;
- [ ] Exibir valor mínimo, máximo e médio;
- [ ] Implementar FFT;
- [ ] Identificar as frequências predominantes;
- [ ] Criar espectro de frequência;
- [ ] Implementar filtros digitais;
- [ ] Implementar filtros FIR;
- [ ] Implementar filtros IIR;
- [ ] Trabalhar com processamento em blocos;
- [ ] Gerar sinal com fase invertida;
- [ ] Implementar reprodução do sinal processado;
- [ ] Medir latência de entrada e saída;
- [ ] Testar cancelamento utilizando fones;
- [ ] Salvar histórico de medições;
- [ ] Criar gráfico de redução de ruído;
- [ ] Melhorar a interface gráfica;
- [ ] Adicionar indicadores por faixa de intensidade sonora.

---

# 📊 Próxima evolução

Uma possível evolução da arquitetura seria:

```text
                    MICROFONE
                        │
                        ▼
                ┌───────────────┐
                │ AudioRecord   │
                └───────┬───────┘
                        │
                        ▼
                ┌───────────────┐
                │ Buffer PCM    │
                └───────┬───────┘
                        │
             ┌──────────┴──────────┐
             │                     │
             ▼                     ▼
       ┌───────────┐         ┌───────────┐
       │    RMS    │         │    FFT    │
       └─────┬─────┘         └─────┬─────┘
             │                     │
             ▼                     ▼
            dB              Frequências
                                   │
                                   ▼
                           ┌───────────────┐
                           │ Processamento │
                           │     DSP       │
                           └───────┬───────┘
                                   │
                                   ▼
                           Inversão de fase
                                   │
                                   ▼
                              AudioTrack
                                   │
                                   ▼
                                🔊 Fone
```

---

# 🎓 Aplicações

Este projeto pode ser utilizado como base de estudo para disciplinas e áreas como:

- Processamento Digital de Sinais;
- Desenvolvimento Mobile;
- Programação Kotlin;
- Física das Ondas;
- Sistemas Embarcados;
- Engenharia de Computação;
- Eletrônica;
- Acústica;
- Processamento de Áudio.

---

# 👨‍💻 Autor

**Bruno Michel Pera**

Desenvolvimento de projetos nas áreas de:

- Desenvolvimento de Software;
- Android/Kotlin;
- Internet das Coisas;
- Robótica;
- Sistemas Embarcados;
- Processamento de Sinais;
- Educação Tecnológica.

GitHub: **@perabru**

---

## ⭐ Apoie o projeto

Se este projeto foi útil para seus estudos ou projetos, considere deixar uma **⭐ Star** no repositório.

Contribuições, sugestões e melhorias são bem-vindas.
