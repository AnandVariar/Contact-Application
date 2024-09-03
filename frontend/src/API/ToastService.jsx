import { toast } from "react-toastify";

const baseToastConfig = {
    position: toast.POSITION,
    autoClose: 1500,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    theme: "light"
};

export function toastInfo(message) {
    toast.info(message, baseToastConfig);
}

export function toastSuccess(message) {
    toast.success(message, baseToastConfig);
}

export function toastWarning(message) {
    toast.warn(message, baseToastConfig);
}

export function toastError(message) {
    toast.error(message, baseToastConfig);
}
