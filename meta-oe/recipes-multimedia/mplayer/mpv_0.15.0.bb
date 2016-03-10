SUMMARY = "Open Source multimedia player"
SECTION = "multimedia"
HOMEPAGE = "http://www.mpv.io/"
DEPENDS = "zlib ffmpeg jpeg virtual/libx11 xsp libxv libxscrnsaver"

# Depends on xsp, libxv, virtual/libx11, libxscrnsaver
REQUIRED_DISTRO_FEATURES = "x11"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=91f1cb870c1cc2d31351a4d2595441cb"

SRC_URI = "https://github.com/mpv-player/mpv/archive/v${PV}.tar.gz;name=mpv \
           http://www.freehackers.org/~tnagy/release/waf-1.8.12;name=waf \
"
SRC_URI[mpv.md5sum] = "9042bd3fbff2bc8ba0b7fadaa4a22101"
SRC_URI[mpv.sha256sum] = "7d31217ba8572f364fcea2955733f821374ae6d8c6d8f22f8bc63c44c0400bdc"
SRC_URI[waf.md5sum] = "cef4ee82206b1843db082d0b0506bf71"
SRC_URI[waf.sha256sum] = "01bf2beab2106d1558800c8709bc2c8e496d3da4a2ca343fe091f22fca60c98b"

EXTRA_OECONF = " \
    --prefix=${D}/usr \
    --mandir=${mandir} \
    --target=${SIMPLE_TARGET_SYS} \
    --disable-gl \
    --disable-libsmbclient \
    --disable-libass \
    --disable-lua \
    --disable-libass-osd \
    --disable-encoding \
    --disable-libbluray \
    --disable-dvdread \
    --disable-dvdnav \
    --disable-cdda \
    --disable-enca \
    --disable-libguess \
    --disable-uchardet \
    --disable-rubberband \
    --disable-lcms2 \
    --disable-vapoursynth \
    --disable-vapoursynth-lazy \
    --enable-libarchive \
"

do_configure() {
    if [ ! -L ../waf ]; then
        chmod a+x ../waf-1.8.12
	ln -s waf-1.8.12 ../waf
    fi
    ../waf configure ${EXTRA_OECONF}
}

do_compile () {
    ../waf build
}

do_install() {
    ../waf install
}

FILES_${PN} += "/usr/"