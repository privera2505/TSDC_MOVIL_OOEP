package com.vinilo.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinilo.view.databinding.FragmentAlbumsBinding

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: FragmentAlbumsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAlbumsBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_albums)
    }
}
