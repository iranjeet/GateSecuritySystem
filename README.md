# GateSecuritySystem

[ResearchPaper.pdf](https://github.com/iranjeet/movie_rating_system/files/4869972/ResearchPaper.pdf)

Abstract: In this growing age of technology, it is
necessary to have a proper gate security system, which
should provide security and having primary intone to
disallow unauthorized intruders into the premises. The
system provides insights into the security aspects of a
hostel, it was an image basis authencate for in or out the
entry of a student The analysis applied on the data
generated will indicate the security loop hold and help
startle the behavior of the inmates of the hostel. The
system can be further linked to guardians or parents who
could be assured to in out OTP for perming their wards
for out permission. This paper is about the smart gate
entry management system which is developed for the
visitors of an organization to supervise and record their
entry related activity. The enre system is developed
with network-based application software running at the
other end.
Keywords: OTP (One Time Password), Web Portal,
Android Application, Barcode


#  I- INTRODUCTION

Technology has changed human existence by
extending life spans and improving communications and
due to advancement in technology the growth and
development of the naon are faster. It has made human
life more comfortable but as everything has its pros and
cons so thus this effective use of technology. It is a
Mobile Application cum Web Access System. It is new
because of its ability to resolve security issues. It helps
the hostel management to keep an eye on unauthorized
intruders for the purpose of security since students
staying in hostels far away from their home. The security
is one of the most important concern to make
environment secure, trustable and reliable in the eyes of
student’s parents or guardians. Guard of the hostel use
Android Application having features of Bar Code
scanning and taking live pic of the student during in and
out from Hostel. When student going out from hostel
then the barcode of id card is the first scan out by the guard along with the live picture of student and the data
is send to the web portal displaying red status ll the same
procedure is repeat out during student’s entry into the
hostel Warden of the college have web access having all
the details of the student not present in the hostel and the
details of guard who take out the attendance of particular
student For Web Portal we are using PhP and User
Interface and the data from both the Mobile application
And Web Portal is access from live server For
connecting Mobile application and Web Portal we make
an API that connects our Web Portal and
Mobile application together fetching all the information
from server. We are using MYSQL database further
integrated with Tomcat server
#  II- DESIGN AND METHODOLOGY
The proposed system will be implemented as
an Android-Based application. Due to the current trend
of smartphone the android application will be best
suited. The application will be built using the Android
IDE. The application will be synchronized with the
database stored at the web server and SQLite. The
database will be used as the main asset of this
application. A database will be maintaining all the
diseases, symptoms, effects, and medicines possible.
# A-Software
The application is designed on the Android software
stack produced by Google. Android[1] is an open source
framework designed for mobile devices. It has an
operating system, middleware, and key programs. The
Android Software development kit provides libraries
needed to interface with the hardware at a high level and
make/deploy Android application. An application is
written in Java and use SQL databases to store persistent
data. We choose Android platform because ofof its ability
to allow multi-processing in background, the polished
Navigation API, and comp ability with other Android
devices. Unlike Diligent systems, this software is
# B- Web Portal
The web application is designed using Php[2] and User
Interface techniques like HTML CSS and Java script.
Php is general purpose open source scraping language
that is especially suited for web application and can be
embedded into an HTML. A hypertext markup language
(HTML)[3] is the major markup language used to
display Web pages on the Internet. In other words, Web
pages are composed of HTML, which is used to display
text, images or other resources through a Web browser
Cascading Style Sheets (CSS) is a standard (or language)
that describes the formatting of markup language pages.
CSS[4] defines formatting for the following document
types:
·HyperText Markup Language (HTML)
·Extensible Hyper Text Markup Language (XHTML)
·Extensible Markup Language (XML)
·Scalable Vector Graphic (SVG)
·XML User Interface Language (XUL)
Javascript[5] is a scripng language, that is used on the
Web. It is used to enhance HTML pages and is
embedded in HTML code. JavaScript is an interpreted
language. So it doesn't need to be compiled. JavaScript
provides web pages in an interactive and dynamic
fashion. This allowing the pages to act to events, reveal
special effects, accept variable text, validate data, create
cookies, detect a user’s browser, etc.
# C- System Design
Guard just need to login first using guard employee
number and password into the mobile applicaon. When
student going out from hostel Guard need to scan out the
BarCode of student id and taking the live picture of
student and save it. The details are submied as aendance
on web portal managed by admin i.e warden of the
hostel keeping track of students coming into the hostels
and also keeps out the record if they return or not
Warden uses web portal for accessing and controlling all
the mechanism and the whole of the data from both
mobile application and web portal is accessed from
MYSQL database integrated with the tomcat server. The
web application is connected with the mobile application
using API (An application programming interface (API)
is a set of protocols, routes, functions and/or commands
that programmers use to develop software or facilitate
interaction between `dissection systems). APIs are
available for both desktop and mobile use. APIs are
typically useful for programming GUI (graphic user
interface) components and allowing a software program
to request as well and accommodate services from
another program.
## III- MOBILE APPLICATION FEATURES
Attendance IN Mechanism

●When student going out from the hostel
●Guard opens the mobile application and clicks on the
scan button, the application asks for scanning barcode
and capturing a live picture.
●On clicking IN button attendance would be submitted
on MySQL database integrated with the server.
Attendance OUT Mechanism
●When student coming IN to the Hostel
●Guard opens the mobile application and clicks on the
scan button, the application asks for scanning barcode
and capturing a live picture.
●On clicking OUT button attendance would be
submitted on MySQL database integrated with the server
IV- WEBSITE FEATURES
When Student is not in the hostel premises
●Status column entry in table attendance details for
student x remains red ll the student x returns back
●The exit of student from hostel is marked by
the guard of the gate and the entry is recorded to the
website that can only be managed
by Admin or warden of the hostel.
When student takes entry to the hostel
●Status column entry in table attendance details for
student becomes green indicating student entry back into
the hostel.
●The entry of student into hostel is marked by
the guard of the gate and the entry is recorded to the
website that can only be managed
by Admin or warden of the hostel.
#  V- CONCLUSION
The objective behind this paper is:
This Project needs Further improvement and more action
on towards the security of the system.
Future work idea is to add on image processing through
machine learning and feature for student special gate
pass which is given by specific teacher via OTP.
Also instead of staff members student can generate their
pass via mobile(OTP BASED) which will be valid for
limited period of me.

# REFERENCES

[1] hps://www.techopedia.com/definion/24406/php-
hypertext -preprocessor-php

[2] hps://www.techopedia.com/definion/1892/hypertext-
markup-language-html

[3] Andy Harris, 2009. PHP 6/ MySQL Programming for the

Absolute Beginner, 1st Edion , ISBN-
10: 1598637983 |ISBN-13: 9781598637984, 2009.

[4] hp://developer.android.com/google/gcm/index.htm
[5] hp://www.godrejsecure.com/SecuritySoluons/InstuonalSe
curity.aspx?id=25&menuid=2812
&cad=495&subcad=624&subsubcad=0&subsubsubcad=0
