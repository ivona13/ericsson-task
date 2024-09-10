Harry-Kart in a special kind of horse racing.

The horses participating have a base speed, they can run with that speed indefinitely.

The track is a 1000 meters loop and is divided in lanes, each horse runs on a lane and every lane has the same length.

The horses run the first loop at their base speed but at the end of each loop they find a power-up or power-down.

The power-ups/downs are numbers, negative or positive, representing how much the horse speeds up or slows down.

Your task is to compute the top 3 ranking.

## Example 1

### Input

**Number of loops:** 3

**Start List:**

| Lane | Horse name | Base speed |

|------|----------------|------------|

| 1 | TIMETOBELUCKY | 10 |

| 2 | CARGO DOOR | 10 |

| 3 | HERCULES BOKO | 10 |

| 4 | WAIKIKI SILVIO | 10 |

**Power-Ups/Downs:**

| Loop | Lane 1 | Lane 2 | Lane 3 | Lane 4 |

|------|--------|--------|--------|--------|

| 1 | 1 | 1 | 0 | -2 |

| 2 | 1 | -1 | 2 | -2 |

### Result

| Position | Horse Name |

|----------|---------------|

| 1st | TIMETOBELUCKY |

| 2nd | HERCULES BOKO |

| 3rd | CARGO DOOR |

## Example 2

### Input

**Number of loops:** 3

**Start List:**

| Lane | Horse name | Base speed |

|------|----------------|------------|

| 1 | TIMETOBELUCKY | 10 |

| 2 | CARGO DOOR | 10 |

| 3 | HERCULES BOKO | 10 |

| 4 | WAIKIKI SILVIO | 10 |

**Power-Ups/Downs:**

| Loop | Lane 1 | Lane 2 | Lane 3 | Lane 4 |

|------|--------|--------|--------|--------|

| 1 | 0 | 0 | 1 | 3 |

| 2 | 10 | 0 | 0 | 1 |

### Result

| Position | Horse Name |

|----------|---------------|

| 1st | WAIKIKI SILVIO|

| 2nd | TIMETOBELUCKY |

| 3rd | HERCULES BOKO |

Input:


```xml
<harryKart>
    <numberOfLoops>3</numberOfLoops>
    <startList>
        <participant>
            <lane>1</lane>
            <name>TIMETOBELUCKY</name>
            <baseSpeed>10</baseSpeed>
        </participant>
        <participant>
            <lane>2</lane>
            <name>CARGO DOOR</name>
            <baseSpeed>10</baseSpeed>
        </participant>
        <participant>
            <lane>3</lane>
            <name>HERCULES BOKO</name>
            <baseSpeed>10</baseSpeed>
        </participant>
        <participant>
            <lane>4</lane>
            <name>WAIKIKI SILVIO</name>
            <baseSpeed>10</baseSpeed>
        </participant>
    </startList>
    <powerUps>
        <loop number="1">
            <lane number="1">0</lane>
            <lane number="2">0</lane>
            <lane number="3">1</lane>
            <lane number="4">3</lane>
        </loop>
        <loop number="2">
            <lane number="1">10</lane>
            <lane number="2">0</lane>
            <lane number="3">0</lane>
            <lane number="4">1</lane>
        </loop>
    </powerUps>
</harryKart>
```

Input 2:


```xml
<harryKart>
    <numberOfLoops>3</numberOfLoops>
    <startList>
        <participant>
            <lane>1</lane>
            <name>TIMETOBELUCKY</name>
            <baseSpeed>10</baseSpeed>
        </participant>
        <participant>
            <lane>2</lane>
            <name>CARGO DOOR</name>
            <baseSpeed>10</baseSpeed>
        </participant>
        <participant>
            <lane>3</lane>
            <name>HERCULES BOKO</name>
            <baseSpeed>10</baseSpeed>
        </participant>
        <participant>
            <lane>4</lane>
            <name>WAIKIKI SILVIO</name>
            <baseSpeed>10</baseSpeed>
        </participant>
    </startList>
    <powerUps>
        <loop number="1">
            <lane number="1">1</lane>
            <lane number="2">1</lane>
            <lane number="3">0</lane>
            <lane number="4">-2</lane>
        </loop>
        <loop number="2">
            <lane number="1">1</lane>
            <lane number="2">-1</lane>
            <lane number="3">2</lane>
            <lane number="4">-2</lane>
        </loop>
    </powerUps>
</harryKart>
```

### Swagger Documentation
You can access the Swagger UI for interactive API documentation at:

```bash
http://localhost:8080/swagger-ui.html
```
The OpenAPI JSON specification can be fetched from:
```bash
http://localhost:8080/v3/api-docs

```
This documentation will contain the list of endpoints with their request and response details.

### Security
The API is secured with Basic Authentication. Use the following credentials to access secured endpoints:

```text
Username: user
Password: password
```
This is an example of how to call the API using curl with basic authentication:
```bash
curl -u user:password http://localhost:8080/api/v1/calculate-winner -X POST -H "Content-Type: application/xml" -d "<harryKart><numberOfLoops>3</numberOfLoops><startList><participant><lane>1</lane><name>TIMETOBELUCKY</name><baseSpeed>10</baseSpeed></participant><participant><lane>2</lane><name>CARGO DOOR</name><baseSpeed>10</baseSpeed></participant><participant><lane>3</lane><name>HERCULES BOKO</name><baseSpeed>10</baseSpeed></participant><participant><lane>4</lane><name>WAIKIKI SILVIO</name><baseSpeed>10</baseSpeed></participant></startList><powerUps><loop number=\"1\"><lane number=\"1\">1</lane><lane number=\"2\">1</lane><lane number=\"3\">0</lane><lane number=\"4\">-2</lane></loop><loop number=\"2\"><lane number=\"1\">1</lane><lane number=\"2\">-1</lane><lane number=\"3\">2</lane><lane number=\"4\">-2</lane></loop></powerUps></harryKart>"
```
