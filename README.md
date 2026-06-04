# Mini CI/CD Pipeline Projekat

## O projektu

Ovo je mali DevOps projekat napravljen u homelab okruženju(Proxmox) sa ciljem upoznavanja i praktične primene CI/CD procesa, automatizovanog deployment-a, monitoringa i osnovnih SysOps zadataka.

Jenkins služi kao centralna tačka sistema i zadužen je za preuzimanje koda sa GitHub-a, Maven build proces, pokretanje Ansible playbook-ova i prikaz monitoringa putem Prometheus, Grafana i Alertmanager alata.

Java aplikacija se deployuje na UbuntuAgent server, dok se FedoraAgent koristi kao dodatni Ansible agent za administrativne i infrastrukturne zadatke.

---

## Infrastruktura

                     | Server | Uloga |   
------------------------------|--------------------------------------------------------------|
               JenkinsMaster  | Jenkins, Maven, Ansible, Prometheus, Grafana i Alertmanager  |
                UbuntuAgent   | Pokretanje Java aplikacije                                   |
                FedoraAgent   | Dodatni Ansible agent                                        |

---

## Tok rada

1. Jenkins preuzima kod iz GitHub repozitorijuma.
2. Maven kompajlira Java aplikaciju i kreira JAR fajl.
3. Ansible izvršava deployment na UbuntuAgent server.
4. Jenkins generiše jednostavan deployment report.
5. Prometheus prikuplja metrike sa servera.
6. Grafana prikazuje metrike kroz dashboarde.
7. Alertmanager obrađuje definisana alert pravila.

---

## Build aplikacije

cd maven-projekat
mvn clean package

Kreirani JAR fajl nalazi se u:
maven-projekat/target/

---

## Pokretanje Ansible playbook-a

ansible-playbook -i ansible/inventory ansible/playbook.yml

---

## Monitoring

Konfiguracioni fajlovi nalaze se u:

prometheus/

├── prometheus.yml
├── alerts.yml
└── alertmanager.yml

Pristup servisima:

Grafana      : http://192.168.100.148:3000
Prometheus   : http://192.168.100.148:9090
Alertmanager : http://192.168.100.148:9093

---

## Deployment Report

Nakon svakog uspešnog deployment-a Jenkins generiše jednostavan CSV izveštaj:

.csv format :
build_number,date,status
1,2026-06-04 20:00:00,SUCCESS

Izveštaj služi za praćenje istorije build-ova i deployment-a.

---

## Struktura projekta


├── ansible/
│   ├── host_vars/
│   │   ├── 192.168.100.161.yml
│   │   └── 192.168.100.184.yml
│   │
│   ├── roles/
│   │   ├── base/
│   │   │   └── tasks/
│   │   │       └── main.yml
│   │   │
│   │   └── maven/
│   │       └── tasks/
│   │           └── main.yml
│   │
│   ├── ansible.cfg
│   ├── inventory
│   └── playbook.yml
│
├── maven-projekat/
│   ├── src/
│   └── pom.xml
│
├── prometheus/
│   ├── prometheus.yml
│   ├── alerts.yml
│   └── alertmanager.yml
│
├── secrets/
│   └── grafana.txt
│
├── Jenkinsfile
├── jenkins_plugins.groovy
├── grafana_dashboard_screenshot.png
└── README.md


---

## Korišćeni alati

- Jenkins
- Maven
- Ansible
- Prometheus
- Grafana
- Alertmanager
- Git & GitHub
- Ubuntu Linux
- Fedora Linux

---

## Cilj projekta

Cilj projekta bio je sticanje praktičnog iskustva sa alatima koji se često koriste u DevOps okruženjima, kao i razumevanje načina na koji CI/CD, automatizacija deployment-a, monitoring i alerting funkcionišu zajedno u jednom sistemu.

Projekat predstavlja jednostavan primer povezivanja više alata u funkcionalan CI/CD pipeline sa osnovnim monitoringom i automatizacijom.
