package com.tech.design_system.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.tech.design_system.common.model.BXMasDialogMessage

@Composable
fun BXMasAlertDialog(
    dialogMessage: BXMasDialogMessage,
    isVisible: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    if (!isVisible.value) return

    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            isVisible.value = false
            dialogMessage.onDismiss?.invoke()
        },
        title = {
            Text(
                text = dialogMessage.title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = dialogMessage.message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    isVisible.value = false
                    dialogMessage.onConfirm()
                }
            ) {
                Text(dialogMessage.confirmLabel)
            }
        },
        dismissButton = dialogMessage.dismissLabel?.let { label ->
            {
                TextButton(
                    onClick = {
                        isVisible.value = false
                        dialogMessage.onDismiss?.invoke()
                    }
                ) {
                    Text(label)
                }
            }
        }
    )
}