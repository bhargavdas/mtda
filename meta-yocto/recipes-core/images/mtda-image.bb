SUMMARY = "Custom image for mtda"
DESCRIPTION = "Custom image for mtda."

# based on core image base
include recipes-core/images/core-image-base.bb

DISTRO_FEATURES:append = " systemd"
VIRTUAL-RUNTIME_init_manager += "systemd"

#inherit extrausers
#EXTRA_USERS_PARAMS = "useradd -P mtda mtda;"

IMAGE_INSTALL:append = " \
        git \
        zstandard \
        zerorpc-python \
        mtda \
        python3-daemon \ 
        python3-zopeinterface \
        openssh \
        sd-mux-ctrl \
        pduclient \
        python3-zeroconf \
        python3-py3qterm \
        mjpg-streamer \
"