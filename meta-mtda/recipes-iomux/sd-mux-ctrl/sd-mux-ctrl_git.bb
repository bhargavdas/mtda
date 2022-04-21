SUMMARY = "sd-mux stands for Secure Digital Multiplexer"
DESCRIPTION = "This is SD card switcher (multiplexer) designed to help automatic testing."
HOMEPAGE = " https://wiki.tizen.org/SD_MUX "
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=064ad0ceb0d2be1dd2e7fc4ba1b9de6f"

SRC_URI += "git://git.tizen.org/cgit/tools/testlab/sd-mux;protocol=https"
SRCREV   = "204f4f2e7e02addabe2e221b3b6c7bb91997e2a2"
S        = "${WORKDIR}/git"

DEPENDS = "libftdi popt"

inherit cmake
