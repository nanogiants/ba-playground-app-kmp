
# Kotlin Multiplatform - Analyse und Vergleich zur nativen App Entwicklung

> INFO: Diese Ausarbeitung wurde im Dezember 2019 erstellt. Kotlin Multipaltform befindet sich derzeit im Status *experimental* und somit noch in der Entwicklung.

Analyse der Technologie Kotlin-Multiplatform für die Entwicklung mobiler Anwendungen und Vergleich zur nativen App-Entwicklung.

# Zusammenfassung
Apps stellen Entwickler vor die Herausforderung, dass mit Android und iOS zwei unterschiedliche und inkompatible Betriebssysteme verbreitet sind. Es kann zwar auf Cross-Plattform Frameworks und hybride Technologien zurückgegriffen werden um nicht jede App doppelt entwickeln zu müssen, aber diese ersetzen die native Entwicklung nicht gleichwertig. Die Programmiersprache Kotlin bietet mit Kotlin Multiplatform eine neue Alternative zu bestehenden Vorgehensweisen.

Deshalb wird hier untersucht, ob sich Kotlin Multiplatform für die professionelle Entwicklung mobiler Anwendungen eignet. Dies kann als eine Entscheidungsgrundlage dienen, inwieweit sich ein Umstieg von der nativen Entwicklung zu Kotlin Multiplatform aktuell (Stand Dezember 2019) für die professionelle Entwicklung von Apps im Bereich B2B und B2C lohnt.

Zunächst werden dazu Kriterien identifiziert, die eine Technologie für die App-Entwicklung erfüllen muss. Im nächsten Schritt wird eine mobile Anwendung konzipiert. Diese wird nativ für Android, nativ für iOS und mit Kotlin Multiplatform implementiert. Im Folgenden werden die Technologien anhand der zuvor entwickelten Kriterien verglichen.

Es stellt sich heraus, dass Kotlin Multiplatform zurzeit nicht alle Anforderungen ausreichend erfüllt. Die Technologie bietet großes Potential, denn sie reduziert insbesondere durch das Teilen von Code zwischen Android und iOS den Entwicklungs- und Wartungsaufwand. Die jederzeit möglichen Änderungen am Software Development Kit (zurzeit experimental) und die eingeschränkte Unterstützung durch Entwicklungswerkzeuge verhindern aktuell jedoch noch den produktiven Einsatz. In Zukunft wird eine erneute Evaluation stattfinden.

# Inhalt
* Das [Wiki](https://github.com/nanogiants/ba-playground-app-kmp/wiki) beeinhaltet die ausführliche Analyse von Kotlin Multiplatform
* Das Repository enthält die dafür erstellen Beispielanwendungen.


# Lizenz
```
Copyright (C) 2020 NanoGiants GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```