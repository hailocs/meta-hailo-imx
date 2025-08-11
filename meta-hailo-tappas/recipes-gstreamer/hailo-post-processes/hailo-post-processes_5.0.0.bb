DESCRIPTION = "Tappas post processes \
               compiles the hailo post processes, including draw processes, cropping algorithms and various network postprocesses \
               and copies it to usr/lib/hailo-post-processes"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://github.com/hailocs/tappas-imx.git;protocol=https;branch=dev-tappas-5.0"
SRCREV = "3f2c9bc9c26e2a603e40443ef6deb94e541195a5"

inherit hailotools-base

# Setting meson build target
TAPPAS_BUILD_TARGET = "libs"
ROOTFS_POST_PROCESSES_DIR = "${libdir}/hailo-post-processes"

# add dependencies
DEPENDS += "cxxopts rapidjson"
RDEPENDS:${PN} += " libgsthailotools"


# meson configuration
EXTRA_OEMESON += " \
        -Dpost_processes_install_dir='${ROOTFS_POST_PROCESSES_DIR}' \
        -Dlibcxxopts='${STAGING_INCDIR}/cxxopts' \
        -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
        "

do_install:append() {
    # Meson installs shared objects in apps target,
    # we remove it from the rootfs to prevent duplication with libgsthailotools
    rm -rf ${D}/usr/lib/libhailo_tracker*
}

FILES:${PN} += "${libdir}/hailo-post-processes/* ${ROOTFS_POST_PROCESSES_DIR}/* ${ROOTFS_POST_PROCESSES_DIR}/so.* \
                ${ROOTFS_POST_PROCESSES_DIR}/cropping_algorithms/* ${ROOTFS_POST_PROCESSES_DIR}/post_processes_data/* "
