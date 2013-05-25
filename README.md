# Mothership
A (at least until now) very little http api wrapper around flying saucer.

Accepts a PUT request to the root path with a `document` parameter that contains a valid XHTML document (attention:
html 5 is not supported, as the document needs to be well-formed xml) and converts the given html to a PDF file.

CSS Style sheets can be embedded in the HTML document.

Once started, the server will listen on Port `33602` for incoming connections.

# Examples:
 ```
POST / HTTP/1.1
Host: localhost:33602
Content-Type: application/x-www-form-urlencoded

document=++++++++%3Chtml%3E%0D%0A++++++++++++%3Chead%3E%3C%2Fhead%3E%0D%0A++++++++++++%3Cbody%3E%0D%0A++++++++++++++++%3Ch1%3EDemo+HTML+page%3C%2Fh1%3E%0D%0A++++++++++++++++%3Cp%3Eedit+me+to+see+mothership+in+action%3C%2Fp%3E%0D%0A++++++++++++%3C%2Fbody%3E%0D%0A++++++++%3C%2Fhtml%3E%0D%0A++++
 ```

 ```
curl "http://localhost:33602/" -H "Origin: http://localhost:33602" -H "Accept-Encoding: gzip,deflate,sdch" -H "Host: localhost:33602" -H "Accept-Language: en-US,en;q=0.8" -H "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36" -H "Content-Type: application/x-www-form-urlencoded" -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" -H "Cache-Control: max-age=0" -H "Referer: http://localhost:33602/" -H "Connection: keep-alive" --data "document=++++++++%3Chtml%3E%0D%0A++++++++++++%3Chead%3E%3C%2Fhead%3E%0D%0A++++++++++++%3Cbody%3E%0D%0A++++++++++++++++%3Ch1%3EDemo+HTML+page%3C%2Fh1%3E%0D%0A++++++++++++++++%3Cp%3Eedit+me+to+see+mothership+in+action%3C%2Fp%3E%0D%0A++++++++++++%3C%2Fbody%3E%0D%0A++++++++%3C%2Fhtml%3E%0D%0A++++"
 ```

# Building

Building project mothership is as simple as `mvn clean install`. The maven structure is extremely simple, so any
IDE maven plugin should be able to import this project.

# Running

The main class is `de.mh.mothership.Mothership`


# Libraries

libraries used are:
* google guice as application framework
* google guava (for various little shorthands)
* jetty as embedded webserver
* xhtmlrenderer to generate the pdf files
