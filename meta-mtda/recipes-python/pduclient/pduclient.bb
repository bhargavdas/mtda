SUMMARY = "recipe to install pduclient"
DESCRIPTION = "recipe to install pduclient"

LICENSE = "GPL-2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"


FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI = "file://pduclient"
S = "${WORKDIR}"

do_install(){
    install -d ${D}${bindir}
    cp -r ${WORKDIR}/pduclient ${D}${bindir}
}

FILES:${PN} = "${bindir}"

INSANE_SKIP:${PN} = "file-rdeps"
